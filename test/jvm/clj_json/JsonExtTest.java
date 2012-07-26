package clj_json;

import java.io.BufferedReader;
import java.io.StringReader;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParser;
import org.junit.Test;

public class JsonExtTest {

  @Test
  public void test_parseAsArray() throws Exception {
    JsonFactory jsonFactory = new JsonFactory();
    JsonParser parser = jsonFactory.createJsonParser(new BufferedReader(new StringReader("[{\"a\": 1}, {\"b\": 2}]")));
    {
      Object object = JsonExt.parseAsArray(parser, false, new Object());
      System.out.println(object.getClass());
    }
    {
      Object object = JsonExt.parseAsArray(parser, false, new Object());
      System.out.println(object.getClass());
    }
    {
      Object object = JsonExt.parseAsArray(parser, false, new Object());
      System.out.println(object.getClass());
    }
  }

}
