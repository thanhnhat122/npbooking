package hcmute.edu.booking.filter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Filter {
  private int province;
  private Date dateIn;
  private Date dateOut;
  private List<Integer> star = new ArrayList<Integer>();
  private boolean sustainTour;
  private boolean sea;
  private int score ;
  private double priceMin ;
  private double priceMax;
  private int numPeople;
  private int distanceCenter;

  public Filter() {
  }

  public Filter(int province, Date dateIn, Date dateOut, List<Integer> star, boolean sustainTour, boolean sea, int score, double priceMin, double priceMax,int numPeople,int distanceCenter) {
    this.province = province;
    this.dateIn = dateIn;
    this.dateOut = dateOut;
    this.star = star;
    this.sustainTour = sustainTour;
    this.sea = sea;
    this.score = score;
    this.priceMin = priceMin;
    this.priceMax = priceMax;
    this.numPeople = numPeople;
    this.distanceCenter = distanceCenter;
  }

  public int getProvince() {
    return province;
  }

  public void setProvince(int province) {
    this.province = province;
  }

  public Date getDateIn() {
    return dateIn;
  }

  public void setDateIn(Date dateIn) {
    this.dateIn = dateIn;
  }

  public Date getDateOut() {
    return dateOut;
  }

  public void setDateOut(Date dateOut) {
    this.dateOut = dateOut;
  }

  public List<Integer> getStar() {
    return star;
  }

  public void setStar(List<Integer> star) {
    this.star = star;
  }

  public boolean isSustainTour() {
    return sustainTour;
  }

  public void setSustainTour(boolean sustainTour) {
    this.sustainTour = sustainTour;
  }

  public boolean isSea() {
    return sea;
  }

  public void setSea(boolean sea) {
    this.sea = sea;
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public double getPriceMin() {
    return priceMin;
  }

  public void setPriceMin(double priceMin) {
    this.priceMin = priceMin;
  }

  public double getPriceMax() {
    return priceMax;
  }

  public void setPriceMax(double priceMax) {
    this.priceMax = priceMax;
  }

  public int getNumPeople() {
    return numPeople;
  }

  public void setNumPeople(int numPeople) {
    this.numPeople = numPeople;
  }

  public int getDistanceCenter() {
    return distanceCenter;
  }

  public void setDistanceCenter(int distanceCenter) {
    this.distanceCenter = distanceCenter;
  }
}
