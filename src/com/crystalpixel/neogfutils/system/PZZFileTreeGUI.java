package com.crystalpixel.neogfutils.system;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import com.crystalpixel.neogfutils.utils.math.DynamicByteBuffer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class PZZFileTreeGUI extends JFrame {
    private JTree fileTree;
    private JPopupMenu popupMenu;
    private final int DATA_OFFSET = 0x800;

    public PZZFileTreeGUI() {
        setTitle("PZZ Viewer");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        fileTree = new JTree();
        JScrollPane treeScrollPane = new JScrollPane(fileTree);
        add(treeScrollPane, BorderLayout.CENTER);

        popupMenu = new JPopupMenu();
        JMenuItem decompressMenuItem = new JMenuItem("Decompress");
        decompressMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onDecompressMenuItemClick();
            }
        });
        popupMenu.add(decompressMenuItem);

        fileTree.setComponentPopupMenu(popupMenu);

        loadPZZFileTree("D:/Bordel/GotchaForce/FULL_AFS_FILE_DUMP/pl0708.pzz");

        setVisible(true);
    }

    private void loadPZZFileTree(String filePath) {
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(filePath, "r")) {
            int fileCount = randomAccessFile.readInt();

            int offsetIndex = DATA_OFFSET;
    
            DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");

            for (int i = 0; i < fileCount; i++) {
                randomAccessFile.seek(4 * i + 4);
                int fileDescriptor = randomAccessFile.readInt();

                boolean isCompressed = (fileDescriptor & 0x40000000) != 0;
                int fileLength = (fileDescriptor & 0x3FFFFFFF) * 0x800;

                if (fileLength > 0) {
                    byte[] fileData = new byte[fileLength];
                    randomAccessFile.seek(offsetIndex);
                    randomAccessFile.read(fileData);
                    offsetIndex += fileLength;
                
                    String fileName = "File " + (i + 1) + " - " + (isCompressed ? "Compressed" : "Uncompressed") + " - " + fileLength + " bytes";
                    DefaultMutableTreeNode fileNode = new DefaultMutableTreeNode(fileName);
                    fileNode.setUserObject(new FileNodeData(fileData, isCompressed));
                    root.add(fileNode);
                } else {
                    System.out.println("Skipping file " + (i + 1) + " because its length is zero.");
                }
            }

            fileTree.setModel(new JTree(root).getModel());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void onDecompressMenuItemClick() {
        TreePath path = fileTree.getSelectionPath();
        if (path != null) {
            DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) path.getLastPathComponent();
            Object userObject = selectedNode.getUserObject();
            if (userObject instanceof FileNodeData) {
                FileNodeData fileNodeData = (FileNodeData) userObject;
                if (fileNodeData.isCompressed()) {
                    byte[] decompressedData = pzzDecompress(fileNodeData.getData());

                    int fileIndex = selectedNode.getParent().getIndex(selectedNode) + 1;
                    String outputFilePath = String.format("D:/Bordel/GotchaForce/FULL_AFS_FILE_DUMP/%dpl0708.dat", fileIndex);
                    try (FileOutputStream outputStream = new FileOutputStream(outputFilePath)) {
                        outputStream.write(decompressedData);
                        JOptionPane.showMessageDialog(this, "File successfully decompressed and saved as " + outputFilePath);
                    } catch (IOException e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(this, "Error saving decompressed file.");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "The file is not compressed.");
                }
            }
        }
    }      

    public static byte[] pzzDecompress(byte[] compressedBytes) {
        ByteBuffer compressedBuffer = ByteBuffer.wrap(compressedBytes);
        DynamicByteBuffer uncompressedBuffer = new DynamicByteBuffer();

        while (compressedBuffer.position() < compressedBuffer.limit()) {
            int cb = compressedBuffer.getShort() & 0xFFFF;

            for (int cbBit = 15; cbBit >= 0; cbBit--) {
                int compressFlag = (cb >> cbBit) & 1;

                if (compressFlag == 1) {
                    int c = compressedBuffer.getShort() & 0xFFFF;

                    int offset = (c & 0x7FF) * 2;
                    if (offset == 0) {
                        return uncompressedBuffer.array();
                    }

                    int count = (c >> 11) * 2;
                    if (count == 0) {
                        c = compressedBuffer.getShort() & 0xFFFF;
                        count = c * 2;
                    }

                    int position = uncompressedBuffer.position() - offset;
                    for (int j = 0; j < count; j++) {
                        uncompressedBuffer.put(uncompressedBuffer.get(position + j));
                    }
                } else {
                    uncompressedBuffer.putShort(compressedBuffer.getShort());
                }
            }
        }

        return Arrays.copyOf(uncompressedBuffer.array(), uncompressedBuffer.position());
    }   

    private static class FileNodeData {
        private final byte[] data;
        private final boolean compressed;

        public FileNodeData(byte[] data, boolean compressed) {
            this.data = data;
            this.compressed = compressed;
        }

        public byte[] getData() {
            return data;
        }

        public boolean isCompressed() {
            return compressed;
        }

        public String toString() {
            return "File - " + (isCompressed() ? "Compressed" : "Uncompressed") + " - " + data.length + " bytes";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PZZFileTreeGUI());
    }
}
