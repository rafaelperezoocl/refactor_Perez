import java.util.*;
import java.util.stream.Collectors;


public class WordFrequencyGame {
    public static final int INIT_COUNT = 1;
    public String getResult(String inputStr){


        if (splitInput(inputStr).length== INIT_COUNT) {
            return inputStr + " 1";
        } else {

            try {


                List<Input> inputList = convertToWordFrequencyList(inputStr);

                //get the map for the next step of sizing the same word
                Map<String, List<Input>> map =getListMap(inputList);

                List<Input> list = new ArrayList<>();
                for (Map.Entry<String, List<Input>> entry : map.entrySet()){
                    Input input = new Input(entry.getKey(), entry.getValue().size());
                    list.add(input);
                }
                inputList = list;

                inputList.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());

                StringJoiner joiner = new StringJoiner("\n");
                for (Input w : inputList) {
                    String s = w.getValue() + " " +w.getWordCount();
                    joiner.add(s);
                }
                return joiner.toString();
            } catch (Exception e) {


                return "Calculate Error";
            }
        }
    }

    private List<Input> convertToWordFrequencyList(String inputStr) {
        String[] inputWords = splitInput(inputStr);

        return Arrays.asList(inputWords).stream()
     .map(word -> new Input(word, INIT_COUNT))
             .collect(Collectors.toList());
    }

    private String[] splitInput(String inputStr) {
        return inputStr.split("\\s+");
    }


    private Map<String,List<Input>> getListMap(List<Input> inputList) {
        Map<String, List<Input>> map = new HashMap<>();
        for (Input input :  inputList){
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!map.containsKey(input.getValue())){
                ArrayList arr = new ArrayList<>();
                arr.add(input);
                map.put(input.getValue(), arr);
            }

            else {
                map.get(input.getValue()).add(input);
            }
        }


        return map;
    }


}
