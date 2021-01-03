package com.joe.command;

import java.util.function.Consumer;

import org.junit.Assert;
import org.junit.Test;

/**
 * 命令引擎测试
 *
 * @author JoeKerouac
 * @version 2019年08月29日 17:49
 */
public class CommandEngineTest {

    @Test
    public void doExec() {
        run(engine -> {
            String help = engine.exec("help help");
            System.out.println(help);
            Assert.assertNotNull(help);
        });
    }

    @Test
    public void doCommandList() {
        run(engine -> {
            String[] commandList = engine.commandList();
            Assert.assertNotNull(commandList);
            Assert.assertTrue(commandList.length > 0);
        });
    }

    @Test
    public void doRegisterCommand() {
        run(engine -> {
            Command command = new Command() {
                @Override
                public String exec(CommandContext input, CommandEngine engine, Environment environment) {
                    return "param:" + input.params().get(0).value();
                }

                @Override
                public String name() {
                    return "test";
                }

                @Override
                public String help() {
                    return "help";
                }
            };
            engine.registerCommand(command);

            Assert.assertEquals("param:param", engine.exec("test -param"));
            Assert.assertEquals("help", engine.exec("help test"));
        });
    }

    @Test
    public void doUnregister() {
        run(engine -> {
            Command command = new Command() {
                @Override
                public String exec(CommandContext input, CommandEngine engine, Environment environment) {
                    return "param:" + input.params().get(0).value();
                }

                @Override
                public String name() {
                    return "test";
                }

                @Override
                public String help() {
                    return "help";
                }
            };
            engine.registerCommand(command);
            command = engine.unregister("test");
            Assert.assertNotNull(command);
            Assert.assertNotEquals("param:param", engine.exec("test -param"));
            Assert.assertNotEquals("help", engine.exec("help test"));
        });
    }

    @Test
    public void doSearchCommand() {
        run(engine -> {
            Command command = new Command() {
                @Override
                public String exec(CommandContext input, CommandEngine engine, Environment environment) {
                    return "param:" + input.params().get(0).value();
                }

                @Override
                public String name() {
                    return "test";
                }

                @Override
                public String help() {
                    return "help";
                }
            };
            engine.registerCommand(command);
            command = engine.searchCommand("test");
            Assert.assertNotNull(command);
        });
    }

    @Test
    public void doTestEnv() {
        run(engine -> {
            Command setCommand = new Command() {
                @Override
                public String exec(CommandContext input, CommandEngine engine, Environment environment) {
                    String[] pair = input.params().get(0).value().split("=");
                    environment.put(pair[0], pair[1]);
                    return "";
                }

                @Override
                public String name() {
                    return "set";
                }

                @Override
                public String help() {
                    return "set key=value";
                }
            };

            Command echoCommand = new Command() {
                @Override
                public String exec(CommandContext input, CommandEngine engine, Environment environment) {
                    String value = input.params().get(0).value();
                    if (value.startsWith("$")) {
                        return String.valueOf(environment.<Object>getEnv(value.substring(1)));
                    } else {
                        return value;
                    }
                }

                @Override
                public String name() {
                    return "echo";
                }

                @Override
                public String help() {
                    return "echo";
                }
            };

            engine.registerCommand(setCommand);
            engine.registerCommand(echoCommand);
            engine.exec("set key=1");
            String key = engine.exec("echo $key");
            Assert.assertEquals("1", key);
        });
    }

    private void run(Consumer<CommandEngine> consumer) {
        consumer.accept(CommandEngineFactory.build());
    }
}
