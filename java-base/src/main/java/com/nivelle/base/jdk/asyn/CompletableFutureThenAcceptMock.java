package com.nivelle.base.jdk.asyn;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * TODO:DOCUMENT ME!
 *
 * @author fuxinzhong
 * @date 2021/04/03
 */
public class CompletableFutureThenAcceptMock {

    public static void main(String[] args) throws Exception {
        thenAccept();
    }

    public static void thenAccept() throws Exception {
        CompletableFuture<String> oneFuture = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                try {
                    Thread.sleep(3000);

                } catch (InterruptedException e) {
                }
                return "one ok";
            }
        });
        System.out.println("one exec after");
        System.out.println("oneFuture after value:" + oneFuture.get());
        //基于thenRun()实现异步任务A，执行完毕后，激活异步任务B，这种激活的任务B能够获取A的执行结果的
        CompletableFuture twoFuture = oneFuture.thenAccept(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("tow accept one value:" + s);
            }
        });

        System.out.println("两个关键步骤执行完成：" + twoFuture.get());

    }
}