 * Traverse.java是大丰银行项目繁简体转换的工具类，dirName写入大丰银行转码目录
 * traverseFolder2 方法里面的srcDir写入实际项目地址，targetDir写入生成繁体文件的路径。
 * 方法执行成功后，会在targetDir下生成大丰的繁体版本的代码，打开targetDir下面的工程，直接build即可生成
 * 对应的繁体的war.(只是把mini启动项目里面的文件全部换成了繁体，并没改变依赖的jar)
 * java版本jdk1.8以上