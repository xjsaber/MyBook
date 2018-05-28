package com.xjsaber.java.java8_in_action.ch5;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Homework {

    public static void main(String[] args){
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );


        // 找出2011年的所有交易并按交易额排序
//        List<Transaction> tr2011 = transactions.stream().filter(t -> t.getYear() == 2011).sorted(Comparator.comparing(Transaction::getValue)).collect(toList());
//        for (Transaction item : tr2011) {
//            System.out.println(item.getYear() + "年：" + item.getValue());
//        }
        // 交易员都在哪些不同的城市工作过
//        List<String> cities = transactions.stream().map(transaction -> transaction.getTrader().getCity())
//                .distinct()
//                .collect(toList());
//        for (String item : cities) {
//            System.out.println(item);
//        }
        // 查找所有来自剑桥的交易员，并按姓名排序
//        List<Trader> traders = transactions.stream().map(Transaction::getTrader).filter(t -> "Cambridge".equals(t.getCity())).distinct().sorted(Comparator.comparing(Trader::getName)).collect(toList());
//        for (Trader item : traders) {
//            System.out.println(item.getName() + ":" + item.getCity());
//        }
        // 返回所有交易员的姓名字符串，按字母顺序排序
//        List<String> tradersName = transactions.stream().map(Transaction::getTrader).map(Trader::getName).distinct().sorted().collect(toList());
//        for (String item : tradersName) {
//            System.out.println(item);
//        }
        // 有没有交易员在米兰工作的
//        List<Trader> traders = transactions.stream().map(Transaction::getTrader).filter(trader -> "Milan".equals(trader.getCity())).distinct().collect(Collectors.toList());
//        for (Trader item : traders) {
//            System.out.println(item.getName());
//        }
        // 打印生活在剑桥的交易员的所有交易额
        Optional<Integer> sum = transactions.stream().filter(transaction -> transaction.getTrader().getCity() == "Cambridge").map(Transaction::getValue).reduce(Integer::sum);
        System.out.println(sum);
        // 所有交易中，最高的交易额是多少
//        Optional<Integer> result = transactions.stream().map(Transaction::getValue).reduce(Integer::max);
//        if (result.isPresent()){
//            Integer value = result.get();
//            System.out.println(value);
//        }
        // 找到交易额最小的交易
//        Optional<Transaction> transaction = transactions.stream().min(Comparator.comparing(Transaction::getValue));
//        if (transaction.isPresent()){
//            Trader trader = transaction.get().getTrader();
//            System.out.println(trader.getName() + ":" + trader.getCity());
//        }
    }
}
