package com.sds;

import java.util.ArrayList;
import java.util.List;

public class BowlingGame {
    private final List<Integer> rolls;

    public BowlingGame() {
        rolls = new ArrayList<>();
    }

    public void roll(Integer pin) {
        if(pin < 0 || pin > 10)
            throw new IllegalArgumentException();

        rolls.add(pin);
    }

    public Integer score() {
        List<Frame> frames = new ArrayList<>();
        for(int i=0; i<rolls.size();i++) {
            if(rolls.get(i) == 10 && frames.size() < 10) {
                frames.add(new Frame(10, 0));
            }else if(i == rolls.size()-1){
                frames.add(new Frame(rolls.get(i),0));
            }
            else {
                Frame frame = new Frame(rolls.get(i), rolls.get(i+1));
                frames.add(frame);
                i++;
            }
        }
        if(frames.size() > 10){
            if(frames.size() == 11 && frames.get(9).getRoll1() == 10) {

            }
            else if(frames.size() == 11 && frames.get(9).baseScore() == 10 && frames.get(10).getRoll2() == 0){

            }
            else throw new IllegalArgumentException();
        }

        Integer baseScore = frames.stream().mapToInt(Frame::baseScore).sum();
        Integer bonusScore = 0;
        for(int i=0; i<frames.size(); i++){
            Frame frame = frames.get(i);
            if(frame.baseScore() > 10 && i <10) throw new IllegalStateException();
            if(i >= 9) continue;

            if(frame.isSpare()) {
                bonusScore += frames.get(i+1).getRoll1();
            }
            if(frame.isStrike()) {
                int bonusFirst = frames.get(i+1).getRoll1();
                bonusScore += bonusFirst;
                if(bonusFirst == 10) bonusScore += frames.get(i+2).getRoll1();
                else bonusScore += frames.get(i+1).getRoll2();
            }


        }
        return baseScore + bonusScore;
    }
}
