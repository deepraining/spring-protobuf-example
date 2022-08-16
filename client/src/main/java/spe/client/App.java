package spe.client;

import com.google.protobuf.TextFormat;
import spe.client.proto.DemoProto;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class App {
  public static void main(String[] args) throws IOException {
    URL url = new URL("http://localhost:8080/testProto");
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    connection.setRequestProperty("Content-Type", "application/octet-stream");
    connection.setRequestMethod("POST");
    connection.setDoOutput(true);
    connection.setDoInput(true);

    OutputStream reqStream = connection.getOutputStream();

    DemoProto.ReqProto.Builder builder = DemoProto.ReqProto.newBuilder();
    builder.setPageNum(5);
    builder.setSearchKey("搜搜");
    builder.setSearchType(2);
    builder.build().writeTo(reqStream);

    connection.connect();

    InputStream resStream = connection.getInputStream();
    DemoProto.ResProto resProto = DemoProto.ResProto.parseFrom(resStream);
    System.out.println("响应数据：");
    System.out.println(TextFormat.printToUnicodeString(resProto));
  }
}
