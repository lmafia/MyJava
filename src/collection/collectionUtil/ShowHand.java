package collection.collectionUtil;

import com.sun.deploy.util.ArrayUtil;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author lmafia
 */
public class ShowHand {

  private final int PLAY_NUM = 5;
  private String[] types = {"方块", "草花", "红心", "黑桃"};
  private String[] values = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
  private List<String> cards = new LinkedList<String>();
  private String[] players = new String[PLAY_NUM];
  private List[] playersCards = new List[PLAY_NUM];

  public void initCards() {
    for (String type : types) {
      for (String value : values) {
        cards.add(type + value);
      }
    }
    Collections.shuffle(cards);
  }

  public void initPlayer(String... names) {
    if (names.length > PLAY_NUM || names.length < 2) {
      System.out.println("players num is error");
    } else {
      for (int i = 0; i < names.length; i++) {
        players[i] = names[i];
      }
    }
  }

  public void initPlayerCards() {
    for ( int i = 0; i < players.length; i++) {
      if (players[i] != null && !"".equals(players[i])) {
        playersCards[i] = new LinkedList<String>();
      }
    }
  }

  public void showAllCards() {
    cards.forEach(System.out::println);
  }

  public void deliverCard(String first) {
    int firstPos = Arrays.binarySearch(players, first);
    for (int i = firstPos; i < PLAY_NUM; i++) {
      if (players[i] != null) {
        playersCards[i].add(cards.get(0));
        cards.remove(0);
      }
    }
    for (int i = 0; i < firstPos; i++) {
      if (players[i] != null) {
        playersCards[i].add(cards.get(0));
        cards.remove(0);
      }
    }
  }

  public void showPlayerCards() {
    for (int i = 0; i < PLAY_NUM; i++) {
      if (players[i] != null) {
        System.out.println(players[i] + ":  ");
        playersCards[i].forEach( card -> System.out.print(card + "\t"));
      }
      System.out.println();
    }
  }


  public static void main(String[] args) {
    ShowHand sh = new ShowHand();
    sh.initPlayer("A", "B", "C");
    sh.initCards();
    sh.initPlayerCards();
    sh.showAllCards();
    System.out.println("-----------------------------");
    sh.deliverCard("A");
    sh.deliverCard("A");
    sh.deliverCard("A");
    sh.showPlayerCards();
  }
}
