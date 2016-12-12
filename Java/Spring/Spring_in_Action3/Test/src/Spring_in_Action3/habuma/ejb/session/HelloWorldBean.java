package Spring_in_Action3.habuma.ejb.session;

import java.util.Queue;

/**
 * Created by xjsaber on 3/13/2016.
 */
public class HelloWorldBean implements Knight {
    private Quest quest;

    public BraveKnight(Quest quest){
        this.quest = quest;
    }

    public void embarkOnQuest() throws QuestException(){
        quest.embark();
    }
}
