package spe.server.controller;

import com.google.protobuf.TextFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import spe.server.proto.DemoProto;

import java.io.*;
import java.util.Arrays;

@Controller
@RequestMapping("/")
public class DemoController {
  @PostMapping(value = "/testProto", headers = {"content-type=application/octet-stream"})
  @ResponseBody
  public ResponseEntity<byte[]> testProto(InputStream reqStream) throws IOException {
    DemoProto.ReqProto reqProto = DemoProto.ReqProto.parseFrom(reqStream);
    System.out.println("请求数据：");
    System.out.println(TextFormat.printToUnicodeString(reqProto));

    DemoProto.ResProto.Builder builder = DemoProto.ResProto.newBuilder();
    builder.setCode(1);
    builder.setMessage("OK啦");
    builder.setExtraMsg("额外信息");
    builder.addAllData(Arrays.asList(
      DemoProto.ItemProto.newBuilder().setId(10).setName("name10").build(),
      DemoProto.ItemProto.newBuilder().setId(20).setName("名字20").build()
    ));

    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
    return new ResponseEntity<>(builder.build().toByteArray(), httpHeaders, HttpStatus.OK);
  }
}
