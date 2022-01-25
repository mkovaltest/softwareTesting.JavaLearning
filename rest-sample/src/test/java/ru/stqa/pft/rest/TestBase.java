package ru.stqa.pft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.testng.SkipException;

import java.io.IOException;
import java.util.Set;

public class TestBase {
  public Executor getExecutor() {
    return  Executor.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490","");
  }

  boolean isIssueOpen(int issueId) throws IOException {
    String json = getExecutor().execute(Request.Get(String.format("https://bugify.stqa.ru/api/issues/%s.json", issueId))).returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    Set<Issue> issueSet = new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {
    }.getType());
    Issue issue = issueSet.iterator().next();
    if (issue.getState().equals("Resolved")||issue.getState().equals("Closed")){
      return false;
    } else {
      return true;
    }
  }

  public void skipIfNotFixed(int issueId) throws IOException {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }
}