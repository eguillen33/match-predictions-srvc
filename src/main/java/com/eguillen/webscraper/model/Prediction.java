package com.eguillen.webscraper.model;

/*
 * "id": 1.03838732E8, "status": "draft", "created_on": "2022-03-09 23:30:02", "modified_on":
 * "2022-03-10 23:10:08", "league_name": "USA: NBA", "match_id": "1551437", "home_team":
 * "Charlotte Hornets", "away_team": "Boston Celtics", "odd_value": "1.3", "team_home_score": "101",
 * "team_away_score": "115", "match_minute": "FT", "coupon_name": "Coupon 8", "game_prediction":
 * "2", "match_status": "won", "match_date": "10.03.2022", "match_time": "04:00 AM",
 * "match_timestamp": "1646874000", "sport_type": "basketball"
 */
public class Prediction {
  private int id;
  private String status;
  private String leagueName;
  private int matchId;
  private String homeTeam;
  private String awayTeam;
  private double oddValue;
  private int teamHomeScore;
  private int teamAwayScore;
  private String matchMinute;
  private String gamePrediction;
  private String matchStatus;
  private String matchDate;
  private String matchTime;
  private String matchTimestamp;
  private String sportType;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getLeagueName() {
    return leagueName;
  }

  public void setLeagueName(String leagueName) {
    this.leagueName = leagueName;
  }

  public int getMatchId() {
    return matchId;
  }

  public void setMatchId(int matchId) {
    this.matchId = matchId;
  }

  public String getHomeTeam() {
    return homeTeam;
  }

  public void setHomeTeam(String homeTeam) {
    this.homeTeam = homeTeam;
  }

  public String getAwayTeam() {
    return awayTeam;
  }

  public void setAwayTeam(String awayTeam) {
    this.awayTeam = awayTeam;
  }

  public double getOddValue() {
    return oddValue;
  }

  public void setOddValue(double oddValue) {
    this.oddValue = oddValue;
  }

  public int getTeamHomeScore() {
    return teamHomeScore;
  }

  public void setTeamHomeScore(int teamHomeScore) {
    this.teamHomeScore = teamHomeScore;
  }

  public int getTeamAwayScore() {
    return teamAwayScore;
  }

  public void setTeamAwayScore(int teamAwayScore) {
    this.teamAwayScore = teamAwayScore;
  }

  public String getMatchMinute() {
    return matchMinute;
  }

  public void setMatchMinute(String matchMinute) {
    this.matchMinute = matchMinute;
  }

  public String getGamePrediction() {
    return gamePrediction;
  }

  public void setGamePrediction(String gamePrediction) {
    this.gamePrediction = gamePrediction;
  }

  public String getMatchStatus() {
    return matchStatus;
  }

  public void setMatchStatus(String matchStatus) {
    this.matchStatus = matchStatus;
  }

  public String getMatchDate() {
    return matchDate;
  }

  public void setMatchDate(String matchDate) {
    this.matchDate = matchDate;
  }

  public String getMatchTime() {
    return matchTime;
  }

  public void setMatchTime(String matchTime) {
    this.matchTime = matchTime;
  }

  public String getMatchTimestamp() {
    return matchTimestamp;
  }

  public void setMatchTimestamp(String matchTimestamp) {
    this.matchTimestamp = matchTimestamp;
  }

  public String getSportType() {
    return sportType;
  }

  public void setSportType(String sportType) {
    this.sportType = sportType;
  }

}
