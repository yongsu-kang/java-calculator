package service;

import entity.Data;
import repository.CalculatorRepository;

import java.util.ArrayList;
import java.util.List;

public class CalculatorService {
    private final CalculatorRepository calculatorRepository;

    public CalculatorService(CalculatorRepository calculatorRepository) {
        this.calculatorRepository = calculatorRepository;
    }

    // 계산 로직
    public String calculate(String calculationFormula){

        List<String> list = createList(calculationFormula);

        mul(list);
        div(list);
        add(list);
        sub(list);

        String result = list.get(0);
        return result;
    }

    // 조회 로직
    public void showAllResult(){
        List<Data> dataList = calculatorRepository.findAll();
        if (dataList.isEmpty()){
            System.out.println("조회할 값이 없습니다.");
        }
        else {
            for (Data data : dataList) {
                System.out.println(data.getCalculationFormula() + " = " + data.getResult());
            }
        }
    }

    public String saveResult(Data data) {
        String saveResult = calculatorRepository.save(data);
        return saveResult;
    }

    private List<String> createList(String calculationFormula) {
        String[] spaceSplitArray = calculationFormula.split(" ");

        List<String> list = new ArrayList<>();
        for (String spaceSplitValue : spaceSplitArray) {
            list.add(spaceSplitValue);
        }
        return list;
    }

    private void add(List<String> list) {
        while (list.contains("+")){
            int operatorIndex = list.indexOf("+");

            double b = Double.parseDouble(list.remove(operatorIndex + 1));
            list.remove(operatorIndex);
            double a =Double.parseDouble(list.remove(operatorIndex - 1));

            list.add(operatorIndex - 1, String.valueOf(a + b));
        }
    }

    private void sub(List<String> list) {
        while (list.contains("-")){
            int operatorIndex = list.indexOf("-");

            double b = Double.parseDouble(list.remove(operatorIndex + 1));
            list.remove(operatorIndex);
            double a =Double.parseDouble(list.remove(operatorIndex - 1));

            list.add(operatorIndex - 1, String.valueOf(a - b));
        }
    }

    private void mul(List<String> list) {

        while (list.contains("*")){
            int operatorIndex = list.indexOf("*");

            double b = Double.parseDouble(list.remove(operatorIndex + 1));
            list.remove(operatorIndex);
            double a =Double.parseDouble(list.remove(operatorIndex - 1));

            list.add(operatorIndex - 1, String.valueOf(a * b));
        }
    }

    private void div(List<String> list) {
        while (list.contains("/")){
            int operatorIndex = list.indexOf("/");

            double b = Double.parseDouble(list.remove(operatorIndex + 1));
            list.remove(operatorIndex);
            double a =Double.parseDouble(list.remove(operatorIndex - 1));

            list.add(operatorIndex - 1, String.valueOf(a / b));
        }
    }
}