//package AQSTest;
//
//import java.util.concurrent.Semaphore;
//import java.util.concurrent.atomic.AtomicInteger;
//
//public class Leetcode1114 {
//
//    class FizzBuzz {
//        private int n;
//        Semaphore fizz = new Semaphore(0);
//        Semaphore buzz = new Semaphore(0);
//        Semaphore fizzbuzz = new Semaphore(0);
//        Semaphore number = new Semaphore(1);
//
//        public FizzBuzz(int n) {
//            this.n = n;
//        }
//
//        // printFizz.run() outputs "fizz".
//        public void fizz(Runnable printFizz) throws InterruptedException {
//            for (int i = 3; i <= n; i += 3) {
//                if (i % 5 != 0) {
//                    fizz.acquire();
//                    printFizz.run();
//                    number.release();
//                }
//            }
//        }
//
//        // printBuzz.run() outputs "buzz".
//        public void buzz(Runnable printBuzz) throws InterruptedException {
//            for (int i = 5; i <= n; i += 5) {
//                if (i % 3 != 0) {
//                    buzz.acquire();
//                    printBuzz.run();
//                    number.release();
//                }
//            }
//        }
//
//        // printFizzBuzz.run() outputs "fizzbuzz".
//        public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
//            for (int i = 15; i <= n; i+=15) {
//                fizzbuzz.acquire();
//                printFizzBuzz.run();
//                number.release();
//            }
//        }
//
//        // printNumber.accept(x) outputs "x", where x is an integer.
//        public void number(IntConsumer printNumber) throws InterruptedException {
//            for (int i = 1; i <= n; i++) {
//                number.acquire();
//                if (i % 3 != 0 && i % 5 != 0) {
//                    printNumber.accept(i);
//                    number.release();
//                } else if (i % 3 == 0 && i % 5 == 0) {
//                    fizzbuzz.release();
//                } else if (i % 3 == 0) {
//                    fizz.release();
//                } else {
//                    buzz.release();
//                }
//            }
//        }
//    }
//}
