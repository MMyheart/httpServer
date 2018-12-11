package com.myheart;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

/**
 * @author yangxin
 * @date 2018/12/10
 */
public class Start {

    public static void main(String[] args) {
        try {
            Properties prop = new Properties();
            prop.load(new FileInputStream(new File("target/classes/property.properties")));
            ServerSocket serverSocket = new ServerSocket(Integer.parseInt(prop.getProperty("port")));

            Socket socket = serverSocket.accept();
//            InetAddress address=socket.getInetAddress();
//            System.out.println("当前客户端的IP地址："+address.getHostAddress());
//            socket.getOutputStream().write("hjklkhgghj".getBytes());
//            socket.getOutputStream().flush();


//            Socket socket=serverSocket.accept();//一旦调用这个方法它就会处于阻塞的状态，等待客户端的请求信息
//            //如果客户端发送连接请求，这个时候我们会接收客户端的请求，并且我们看到accpet方法会返回一个socket的实例
//            //用来与客户端进行通信
//            //一旦与客户端建立socket通信以后，我们下面就需要在客户端和服务器端实现数据的交互，获取客户端提交的登陆
//            //信息，那么如何获取呢？需要通过输入输出流来实现。
//            //3.获取一个输入流，然后来读取客户信息
//            InputStream is=socket.getInputStream();//字节输入流
//            //为了提升效率可以把它包装成一个字符输入流
//            InputStreamReader isr=new InputStreamReader(is);
//            //我们可以为字符流添加缓冲，以缓冲的方式去进行数据的读取
//            BufferedReader br=new BufferedReader(isr);
//            String info=null;
//            while((info=br.readLine())!=null){
//                System.out.println("我是服务器，客户端说"+info);
//            }
//            socket.shutdownInput();//关闭输入流
//            //4.获取输出流，用来服务器端响应客户端的信息
//            OutputStream os=socket.getOutputStream();
//            PrintWriter pw=new PrintWriter(os);
//            String html = "http/1.1 200 ok\n"
//                    +"\n\n"
//                    ;
//            String content = "<html>\n" +
//                    "\n" +
//                    "<head>\n" +
//                    "<title>我的第一个 HTML 页面</title>\n" +
//                    "</head>\n" +
//                    "\n" +
//                    "<body>\n" +
//                    "<p>body 元素的内容会显示在浏览器中。</p>\n" +
//                    "<p>title 元素的内容会显示在浏览器的标题栏中。</p>\n" +
//                    "</body>\n" +
//                    "\n" +
//                    "</html>\n";
//            pw.write(html + content);
//            pw.flush();
//            //5.关闭相关的资源
//            pw.close();
//            os.close();
//            br.close();
//            isr.close();
//            is.close();
//            socket.close();
//            serverSocket.close();

            InputStreamReader r = new InputStreamReader(socket.getInputStream());
            BufferedReader br = new BufferedReader(r);
            String readLine = br.readLine();
            while(readLine != null && !readLine.equals("")){
                System.out.println("获取到数据：" + readLine);
                readLine = br.readLine();
            }
            String content = "<html>\n" +
                    "\n" +
                    "<head>\n" +
                    "<title>我的第一个 HTML 页面</title>\n" +
                    "</head>\n" +
                    "\n" +
                    "<body>\n" +
                    "<p>body 元素的内容会显示在浏览器中。</p>\n" +
                    "<p>title 元素的内容会显示在浏览器的标题栏中。</p>\n" +
                    "</body>\n" +
                    "\n" +
                    "</html>\n";
            String html = "http/1.1 200 ok\n" +"\n\n"+content;
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            pw.println(html);
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
