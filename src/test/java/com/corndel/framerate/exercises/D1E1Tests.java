package com.corndel.framerate.exercises;

import static org.assertj.core.api.Assertions.assertThat;

import io.javalin.Javalin;
import io.javalin.testtools.JavalinTest;
import org.junit.jupiter.api.Test;

public class D1E1Tests {

  Javalin app = new D1E1().app;

  @Test
  public void GET_index_to_hello_world() {
    JavalinTest.test(
        app,
        (server, client) -> {
          var res = client.get("/success.jpg");
          assertThat(res.code()).isEqualTo(200);
          assertThat(res.header("Content-Type")).matches("image\\/jpeg");
          assertThat(res.body().bytes()).isNotEmpty();
        });
  }
}
