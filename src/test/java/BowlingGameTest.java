import com.sds.BowlingGame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BowlingGameTest {

    @Test
    void gutterGame() {
        //Given : 게임을 진행하는 동안
        BowlingGame game = new BowlingGame();

        //When : 모든 투구에서 핀을 하나도 쓰러뜨리지 못하면
        rollZero(game, 20);

        //Then : 점수는 0점이 된다.
        assertEquals(0, game.score());
    }

    @Test
    void onePin() {
        //Given: 게임을 진행하는 동안
        BowlingGame game = new BowlingGame();

        //When: 첫 투구에서 1점을 기록하고, 나머지는 모두 0점을 기록하면
        game.roll(1);
        rollZero(game, 19);

        //Then: 점수는 1점이 된다.
        assertEquals(1, game.score());
    }

    @Test
    void accurateBowlingUnder0() {
        //Given: 게임을 진행하는 동안
        BowlingGame game = new BowlingGame();

        //Then: 런타임(IllegalArgumentException)가 발생한다.
        assertThrows(IllegalArgumentException.class, () -> {
            //When: 투구시 0개보다 적은 핀을 쓰러뜨리면
            game.roll(-1);
        });
    }

    @Test
    void accurateBowlingOver10() {
        //Given: 게임을 진행하는 동안
        BowlingGame game = new BowlingGame();

        //Then: 런타임(IllegalArgumentException)가 발생한다.
        assertThrows(IllegalArgumentException.class, () -> {
            //When: 투구시 0개보다 적은 핀을 쓰러뜨리면
            game.roll(11);
        });
    }

    @Test
    void spare() {
        //Given: 게임을 진행하는 동안 첫번째 투구에서 7개의 핀을 쓰러트리고
        BowlingGame game = new BowlingGame();
        game.roll(7);

        //When: 두번째 투구에서 나머지 3개의 핀을 쓰러뜨려 스페어를 한다면,
        game.roll(3);

        //Then: 다음 투구에서 쓰러뜨린 핀의 갯수만큼 보너스 점수를 받는다.
        game.roll(1);
        rollZero(game, 17);
        assertEquals(12, game.score());
    }

    @Test
    void strike() {
        //Given: 게임을 진행하는 동안
        BowlingGame game = new BowlingGame();

        //When: 첫 프레임에서 스트라이크를 기록한다
        //두번째 프레임의 투구들은 각 1점을 기록한다.
        //나머지 프레임은 0점을 기록한다.
        game.roll(10);
        game.roll(1);
        game.roll(1);
        rollZero(game,16);

        //Then: 점수는 14점이 된다.
        assertEquals(14, game.score());
    }

    @Test
    void accurateBowlingOverScore(){
        BowlingGame game = new BowlingGame();
        game.roll(4);
        game.roll(7);
        assertThrows(IllegalStateException.class, () ->{game.score();});
    }
    @Test
    void perfectGame(){
        BowlingGame game = new BowlingGame();
        for(int i=0; i<12; i++) game.roll(10);
        assertEquals(300, game.score());
    }

    @Test
    void accurateBowlingOver10Frame(){
        BowlingGame game = new BowlingGame();
        rollZero(game, 18);
        game.roll(10);
        game.roll(3);
        game.roll(0);

        assertEquals(13,game.score());
    }
    @Test
    void realGame(){
        BowlingGame game = new BowlingGame();
        game.roll(10);
        game.roll(9);
        game.roll(1);
        game.roll(7);
        game.roll(0);
        game.roll(9);
        game.roll(1);
        game.roll(10);
        game.roll(10);
        game.roll(8);
        game.roll(2);
        game.roll(10);
        game.roll(9);
        game.roll(1);
        game.roll(9);
        game.roll(1);
        game.roll(7);


        assertEquals(188,game.score());
    }

    @Test
    void accurateBowling4(){
        BowlingGame game = new BowlingGame();
        game.roll(10);
        game.roll(9);
        game.roll(1);
        game.roll(7);
        game.roll(0);
        game.roll(9);
        game.roll(1);
        game.roll(10);
        game.roll(10);
        game.roll(8);
        game.roll(2);
        game.roll(10);
        game.roll(9);
        game.roll(1);
        game.roll(9);
        game.roll(1);
        game.roll(7);
        game.roll(3);

        assertThrows(IllegalArgumentException.class, () ->{game.score();});
    }
    private static void rollZero(BowlingGame game, int count) {
        for(int i=0; i<count; ++i) {
            game.roll(0);
        }
    }
}