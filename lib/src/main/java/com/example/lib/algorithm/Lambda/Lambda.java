package com.example.lib.algorithm.Lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class Lambda {
    private static String[] atp = {"Rafael Nadal",
            "Novak Djokovic",
            "Stanislas Wawrinka",
            "David Ferrer", "Roger Federer",
            "Andy Murray", "Tomas Berdych",
            "Juan Martin Del Potro"};

    public static void main(String[] a) {
        List<String> players = Arrays.asList(atp);

//        players.forEach(new Consumer<String>() {
//            @Override
//            public void accept(String s) {
//                Log.d("tbq", "accept: =====>" + s);
//            }
//        });
        players.forEach((player) -> System.out.print("accept: =====>" + player + "\n"));

        players.forEach(outLog::print);

        Arrays.sort(atp, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        // 1.2 使用 lambda expression 排序 players
        Comparator<String> sortByName = (String s1, String s2) -> (s1.compareTo(s2));
        Arrays.sort(atp, sortByName);

        // 1.3 也可以采用如下形式:
        Arrays.sort(atp, String::compareTo);

        System.out.print("Arrays.sort: ========>" + Arrays.toString(atp) + "\n");


        //invoke doSomeWork using Annonymous class
        WorkerInterfaceTest.execute(new WorkerInterface() {
            @Override
            public void doSomeWork() {
                System.out.println("Worker invoked using Anonymous class");
            }
        });

        //invoke doSomeWork using Lambda expression
        WorkerInterfaceTest.execute(() -> System.out.println("Worker invoked using Lambda expression"));

        // 构造Optional<Integer>
        Optional<Integer> optionalInteger = Optional.of(1);
        // 变换为Optional<String>
        Optional<String> optionalStr = optionalInteger.map(String::valueOf);

//        System.out.print(optionalStr.get());

        // Optional.ifPresent
        Optional.of(new Random().nextInt()) // 获得一个包含随机数的Optional实例
                .filter(i -> i % 2 == 0) // 断言 使用传入的Predicate<T> 保证数据为偶数
                .map(String::valueOf) // 变换 使用传入的Function<T,R>变换类型(Integer -> String)
                .ifPresent(System.out::println); // 若满足条件则使用传入的Consumer<T>进行消费(打印)

        Student student = new Student("111", "222", new Student.Achievement("50", "60", "70", false));
//        Student student = new Student("111", "222", null);
        student = null;
        //        System.out.print(student::getAge);
        Optional.ofNullable(student)
                .map(Student::getAchievement)
//               .orElse(new Student.Achievement("","","",false));
//                .map(Student.Achievement::isPass)
//                .filter(Student.Achievement::isPass)
//                .orElseThrow(()->new Exception("asa"));
                .filter(Student.Achievement::isPass)
//                .orElse(new Student.Achievement("","","",false));
//                .orElseThrow(achievement->System.out.print(""))
                .ifPresent(achievement -> System.out.print(achievement.getEnglish()));
    }


    public static class Example {
        public void test() {
            InterfaceExample com = Example::new;
            Example bean = com.create();
            System.out.println(bean);
        }
    }

    interface InterfaceExample {
        Example create();
    }

    public static class outLog {
        public outLog(String s) {
            System.out.print("outLog======>" + s + "\n");
        }

        public static void print(String s) {
            System.out.print("print======>" + s + "\n");
        }
    }


    //定义一个函数式接口
    @FunctionalInterface
    public interface WorkerInterface {

        public void doSomeWork();

    }

    public static class WorkerInterfaceTest {

        public static void execute(WorkerInterface worker) {
            worker.doSomeWork();
        }
    }

}
