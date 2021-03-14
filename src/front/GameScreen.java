package front;

//
// Класс полностью взят из джава раш
//

public interface GameScreen {
    void setScreenSize(int var1, int var2);

    int getScreenWidth();

    int getScreenHeight();

    void showGrid(boolean var1);

    void showCoordinates(boolean var1);

    void setCellColor(int var1, int var2, Color var3);

    Color getCellColor(int var1, int var2);

    void setCellTextSize(int var1, int var2, int var3);

    int getCellTextSize(int var1, int var2);

    void setCellValue(int var1, int var2, String var3);

    String getCellValue(int var1, int var2);

    void setCellNumber(int var1, int var2, int var3);

    int getCellNumber(int var1, int var2);

    void setCellTextColor(int var1, int var2, Color var3);

    Color getCellTextColor(int var1, int var2);

    void setCellValueEx(int var1, int var2, Color var3, String var4);

    void setCellValueEx(int var1, int var2, Color var3, String var4, Color var5);

    void setCellValueEx(int var1, int var2, Color var3, String var4, Color var5, int var6);

    void showMessageDialog(Color var1, String var2, Color var3, int var4);

    void setScore(int var1);

    void setLives(int var1);

    void setTurnTimer(int var1);

    void stopTurnTimer();

    int getRandomNumber(int var1);

    int getRandomNumber(int var1, int var2);

    void initialize();

    void onMouseLeftClick(int var1, int var2);

    void onMouseRightClick(int var1, int var2);

    void onKeyPress(Key var1);

    void onKeyReleased(Key var1);

    void onTurn(int var1);
}

