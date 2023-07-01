package system;

import enums.BorgColor;
import scriptevents.BorgEvent;
import utils.BorgListUtils;
import utils.StoryUtils;
import utils.Utils;

import java.io.IOException;
import java.nio.IntBuffer;
import java.util.*;
import java.util.stream.Collectors;

public class BorgCombinationCostCalculator {

    public static void main(String[] args) throws IOException {
        int battleId = Utils.getHexInput("Please enter the Battle ID: ");
        IntBuffer scriptAddresses = StoryUtils.getBattleScriptAddresses(battleId).position(2);
        List<BorgEvent> borgEvents = new ArrayList<>();
        Map<Integer, Integer> borgCounts = new TreeMap<>();
        while (scriptAddresses.hasRemaining()) {
            borgEvents.addAll(StoryUtils.readBattleScript(scriptAddresses.get()).stream().filter(se ->
                    se instanceof BorgEvent).map(BorgEvent.class::cast).collect(Collectors.toList()));
        }
        borgEvents.forEach(be -> borgCounts.put(be.getId(), borgCounts.containsKey(be.getId()) ? borgCounts.get(be.getId()) + 1 : 1));

        // Borg Loading Finished - Now calculating Cost...
        int fixedCost = 0;
        for (Map.Entry<Integer, Integer> entry : borgCounts.entrySet()) {
            Integer borgId = entry.getKey();
            if ((borgId & 0x8000) == 0) {
                int count = entry.getValue();
                int enemyBorgCost = (int) (BorgSpecies.getBorgSpecies(borgId).getCosts().get(BorgColor.N)/1.5);
                fixedCost += count * enemyBorgCost;
            }
        }

        int arrayId = 0x8000 | Utils.getHexInput("Please specify the Borg list to use: ");
        int borgId = Utils.getHexInput("Please specify the Borg to use: ");
        int enemyBorgCost = (int) (BorgSpecies.getBorgSpecies(borgId).getCosts().get(BorgColor.N)/1.5);
        fixedCost += borgCounts.get(arrayId) * enemyBorgCost;

        List<BorgSpecies> subBorgSpeciesList = BorgListUtils.getRandomBorgList(arrayId & 0x7fff);
        int index = subBorgSpeciesList.stream().map(BorgSpecies::getId).collect(Collectors.toList()).indexOf(borgId);
        List<Integer> rollIndexes = new ArrayList<>();
        while (index < 256) {
            rollIndexes.add(index);
            index+= subBorgSpeciesList.size();
        }

        List<BorgCombo> borgComboList = new ArrayList<>();

        for (Integer rollIndex : rollIndexes) {
            BorgCombo borgCombo = new BorgCombo();
            for (int id : borgCounts.keySet()) {
                if (id == arrayId || id < 0x8000) continue;
                List<BorgSpecies> tempBorgSpeciesList = BorgListUtils.getRandomBorgList(id & 0x7fff);
                int tempIndex = rollIndex % tempBorgSpeciesList.size();
                BorgSpecies borgSpecies = tempBorgSpeciesList.get(tempIndex);
                borgCombo.getSpeciesCount().put(borgSpecies, borgCounts.get(id));
            }
            if (borgComboList.contains(borgCombo)) continue;
            borgComboList.add(borgCombo);
        }

        for (BorgCombo borgCombo : borgComboList.stream().sorted(Comparator.comparing(BorgCombo::getCost)).collect(Collectors.toList())) {
            System.out.println(String.format("%s: %s", (fixedCost + borgCombo.getCost()) / 10 * 10, borgCombo.toString()));
        }
    }

    private static class BorgCombo {
        private Map<BorgSpecies, Integer> speciesCount = new LinkedHashMap<>();
        private Integer cost;

        public Map<BorgSpecies, Integer> getSpeciesCount() {
            return speciesCount;
        }

        public Integer getCost() {
            if (cost == null) {
                //TODO: Turn this into a one-liner.
                cost = 0;
                speciesCount.forEach((key, value) -> cost += (int) (key.getCost() / 1.5 * value));
            }
            return cost;
        }

        @Override
        public String toString() {
            return speciesCount.keySet().stream().map(BorgSpecies::getNameFormatted).collect(Collectors.joining(" + "));
        }

        @Override
        public boolean equals(Object o) {
            return speciesCount.equals(((BorgCombo) o).getSpeciesCount());
        }
    }
}