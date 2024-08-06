package com.corndel.framerate.exercises;

import static org.assertj.core.api.Assertions.assertThat;

import io.javalin.Javalin;
import io.javalin.testtools.JavalinTest;
import okhttp3.FormBody;
import org.jsoup.Jsoup;
import org.junit.jupiter.api.Test;

public class D2E3Tests {

  Javalin app = new D2E3().app;

  @Test
  public void GET_index_to_hello_world() {
    JavalinTest.test(
        app,
        (server, client) -> {
          var res = client.get("/d2e3");
          assertThat(res.code()).isEqualTo(200);
          assertThat(res.header("Content-Type")).matches("text\\/html");

          var body = res.body().string();
          var document = Jsoup.parse(body);
          var form = document.select("form").first();

          assertThat(form.attr("action")).isEqualTo("/submit");
          assertThat(form.attr("method")).isEqualTo("post");
        });
  }

  @Test
  public void etc() {
    JavalinTest.test(
        app,
        (server, client) -> {
          var res =
              client.request(
                  "/submit",
                  requestBuilder -> {
                    requestBuilder.post(
                        new FormBody.Builder()
                            .add("name", "John Doe")
                            .add("email", "john@example.com")
                            .build());
                  });
          assertThat(res.code()).isEqualTo(200);

          var body = res.body().string();
          assertThat(body).isEqualTo("Received: John Doe, john@example.com");
        });
  }
}
