package org.compass.gdet;
import org.kohsuke.github.*;
import java.util.List;
import java.util.Map;

public class GitHubAPIDemo {
  public static void main( String[] args ) {
    System.out.println("GitHub Data Extration Tool Demo");
    GithubDataExtractionTool git = new GithubDataExtractionTool();
    if (!git.checkConnection()) {
      System.out.println("Error Establishing Connection");
    }
    System.out.println("Successfully Established Connection");
    GHRepository repo = git.getRepository(
      "CompassSoftware/GDET-Tremendous-Trio");
    if (repo != null) {

      System.out.print(GithubDataExtractionTool.getRepositoryMetaData(repo));
      String startSection = String.format("\n\n%32s\n", "").replace(" ", "*");
      String endSection = String.format("%32s\n\n\n", "").replace(" ", "*");

      //Print Commits
      System.out.print(startSection);
      System.out.println();
      System.out.println("COMMITS");
      System.out.print(endSection);
      List<GHCommit> commits =
        GithubDataExtractionTool.getCommits(repo);
      for (GHCommit commit : commits) {
        System.out.print(GithubDataExtractionTool.commitToString(commit));
      }

      //Print Commit Comments
      System.out.print(startSection);
      System.out.println("COMMIT COMMENTS");
      System.out.print(endSection);
      List<GHCommitComment> cComments =
        GithubDataExtractionTool.getCommitComments(repo);
      for(GHCommitComment cComment : cComments) {
        System.out.print(GithubDataExtractionTool.commitCommentToString(cComment));
      }

      //Print Issues
      System.out.print(startSection);
      System.out.println("ISSUES");
      System.out.print(endSection);
      List<GHIssue> issues = GithubDataExtractionTool.getIssues(repo);
      for (GHIssue issue : issues) {
        System.out.print(GithubDataExtractionTool.issueToString(issue));
      }

      //Print Commit Count Per User
      System.out.print(startSection);
      System.out.println("COMMIT-COUNT-PER-USER");
      System.out.print(endSection);
      Map<GHUser, Integer> commitsPerUser =
        GithubDataExtractionTool.getCommitCountPerUser(repo);
      for (GHUser user : commitsPerUser.keySet()) {
        System.out.println("User: "
          + GithubDataExtractionTool.getGHUserNameWithFallback(user) + "    "
          + "Commit Count: " + commitsPerUser.get(user));
      }
    }
  }
}
