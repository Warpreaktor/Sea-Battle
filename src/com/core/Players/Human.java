package com.core.Players;

import com.core.ImageName;
import com.core.Ships.DeckOfShip;
import com.core.Ships.Ship;
import front.App;

import static com.core.SeaBattleGame.setGreenDotsAround;

public class Human extends Player {
    public boolean isCPU() {
        return false;
    }

    public Human() {
        super();
    }

    public boolean shoot(int Y, int X) {
        Player CPU = App.SEA_BATTLE_GAME.getAI();
        Player human = App.SEA_BATTLE_GAME.getHuman();
        if (human.getEnemyFleetMap()[Y][X].getLabel() == '+' || human.getEnemyFleetMap()[Y][X].getLabel() == 'X') {
            return false;
        } else {
            try {
                if (CPU.getOurFleetMap()[Y][X].getClass().getSimpleName().equals("DeckOfShip")) {
                    String enemyShipName = CPU.getOurFleetMap()[Y][X].getName();
                    DeckOfShip enemyShip = (DeckOfShip) CPU.getOurFleetMap()[Y][X];
                    theShipIsDamaged(enemyShip.getShipOwner(), CPU, human, Y, X);
                    if (enemyShip.getHp() > 0) {
                        App.BATTLE_FIELD_CONTROLLER.setReplica(Player.getRandomReplica(Replicas.CPU_DAMAGED));
                        App.BATTLE_FIELD_CONTROLLER.setComicsImage(ReplicasImage.ANGRY_TALK);
                        return false;
                    }
                    if (enemyShip.getHp() <= 0) {
                        theShipIsDestroyed(enemyShip.getShipOwner(), CPU, human);
                        App.BATTLE_FIELD_CONTROLLER.setReplica(Player.getRandomReplica(Replicas.CPU_KILLED));
                        App.BATTLE_FIELD_CONTROLLER.setComicsImage(ReplicasImage.KA_BOOM);
                        return false;
                    }
                } else {
                    App.BATTLE_FIELD_CONTROLLER.setReplica(Player.getRandomReplica(Replicas.HUMAN_MISSED));
                    App.BATTLE_FIELD_CONTROLLER.setComicsImage(ReplicasImage.CALM_TALK);
                    if (human.getEnemyFleetMap()[Y][X].getLabel() == 'X') {

                    } else {
                        human.getEnemyFleetMap()[Y][X].setLabel('+');
                    }
                    missed(CPU, human, Y, X);
                }
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }
    }

    @Override
    public void theShipIsDamaged(Ship ship, Player enemy, Player self, int Y, int X) {
        enemy.getOurFleetMap()[Y][X].setLabel('X');                 //???????????? ???????????? ?? ?????????? ??????????????????
        enemy.getOurFleetMap()[Y][X].setImage(ImageName.RED_CROSS); //???????????? ?????????? ???? ?????????? ??????????????????
        self.getEnemyFleetMap()[Y][X].setLabel('X');                //???????????? ?????????????? ?? ?????????? ?????????? "??????????"
        self.getEnemyFleetMap()[Y][X].setImage(ImageName.RED_CROSS);//???????????? ?????????? ?? ?????????? ?????????? "??????????"
        ship.setHp(ship.getHp()-1);
    }

    @Override
    public void theShipIsDestroyed(Ship ship, Player enemy, Player self) {
        setGreenDotsAround(self.getEnemyFleetMap(), ship);
        setGreenDotsAround(enemy.getOurFleetMap(), ship);
        App.SEA_BATTLE_GAME.playerShipDecrement(enemy);
        App.BATTLE_FIELD_CONTROLLER.stateUpdate();
//        App.BATTLE_FIELD_CONTROLLER.getEnemyShipName().setText(ship.getName());
    }

    @Override
    public void missed(Player enemy, Player self, int Y, int X) {
        enemy.getOurFleetMap()[Y][X].setImage(ImageName.DOT);
        self.getEnemyFleetMap()[Y][X].setLabel('+');
        self.getEnemyFleetMap()[Y][X].setImage(ImageName.DOT);
    }

}
