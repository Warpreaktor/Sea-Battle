package com.core.MapObjects;

import com.core.Tools;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class Chest {
    private ArrayList<Item> items;


    public ArrayList<Item> getItems() {
        return items;
    }

    public Chest() {
        items = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            this.items.add(chestOpen());
        }
    }

    String[] weaponsM = {"Меч", "Моргинштерн", "Кортик", "Арбалет", "Лук", "Молот", "Топор", "Нож", "Кистень", "Серп",
            "Цеп", "Тесак", "Кинжал", "Шестопёр", "Трезубец", "Бердыш", "Буздыхан", "Пернач", "Молотило", "Шест",
            "Клевец", "Кончар", "Эсток", "Стилет", "Багор", "Гарпун", "Дротик", "Пилум", "Джавелин", "Джерид",
            "Полэкс", "Томагавк", "Крис", "Двуручный меч", "Крепкий"};

    String[] weaponsW = {"Булава", "Секира", "Сабля", "Алебарда", "Дубина", "Рогатина", "Праща", "Шпага",
            "Нагамаки", "Палица", "Нунчака", "Шалапуга", "Макила", "Палка", "Кама",
            "Рапира", "Пика", "Острога", "Айболта", "Валашка", "Коса"};

    String[] weaponsIt = {"Копьё", "Зубило", "Дробило", "Мачете", "Хоко", "Гасило"};

    String[] trashM = {"канат", "якорь", "амулет", "компас", "плащ", "глобус", "рюкзак", "сапог", "кувшин", "факел",
                        "подсвечник", "флаг", "пуд соли", "мундир", "мешок с песком", "мещок яблок", "мешок картошки",
                        "мешок с дерьмом", "табак", "камзол",  "таз", "портрет", "ром", "костыль",  "виски"};

    String[] trashW = {"чаша", "трубка", "сеть для ловли", "рубаха", "кастрюля", "шуба", "лодка", "цепочка", "трость",
                        "мантия", "рыбка", "бочка рома", "табакерка", "водка", "клешня", "шкатулка"};

    String[] trashIt = {"Ожерелье", "Пушечное ядро", "Ведро", "Платье", "Веретено", "Весло", "Авокадо", "Знамя",
                        "Вино", "коромысло", "седло"};

    String[] usuallyWeaponNounsM = {"Холодный", "Неплохой", "Пыльный",
            "Утренний", "Бешеный","Жалкий", "Гигантский", "Огромный",
            "Тупой", "Смешной", "Ужасный", "Непристойный"};

    String[] rareWeaponNounsM = {"Изящный", "Алый", "Красивый",
            "Славянский", "Арабский", "Православный", "Мусульманский",
            "Чудовищный", "Гибельный", "Острый",
            "Лихой",
            "Кровожадный"};

    String[] epicWeaponNounsM = {"Вечный", "Волшебный", "Магический",
            "Чудесный", "Богатый", "Расписной",
           "Священный", "Идеальный"};

    String[] usuallyWeaponNounsW = {"Ледяная", "Кровавая", "Уродливая", "Кривая",
            "Гигантская", "Огромная",
            "Тупая", "Извращенная",
            };

    String[] rareWeaponNounsW = {"Ледяная", "Пригожая", "Изящная", "Кровавая",
            "Славянская", "Арабская", "Ладная", "Гигантская", "Огромная",
            "Острая",
            "Злющая", "Колючая",
            };

    String[] epicWeaponNounsW = {
            "Ночная", "Яростная",
            "Чудовищная", "Смертельная",
            "Призрачная", "Роскошная", "Расписная",
            "Дьявольская", "Сумрачная", "Божественная"};

    String[] trashNounsM = {"рваный", "прочный", "ржавый", "тяжелый", "красивый", "драгоценный", "старинный",
                            "потёртый", "привлекательный", "надёжный", "чёрный", "кожаный", "треснувший", "дешёвый",
                            "крепкий", "брошенный", "пыльный", "расписной", "вонючий", "благоухающий", "славянский",
                            "арабский"};

    String[] goodTrashNounsW = {"Изящная", "редкая", "раритетная", "драгоценная",
                            "роскошная", "готичечская", "старинная",
                            "славянская", "арабская", "культурная","необычная", "божья"};

    String[] badTrashNounsW = {"корявая", "потёртая",
            "ненужная", "выкинутая", "дешёвая", "ничтожная",
            "мокрая", "хрупкая",};

    String[] usuallyNounsIt = {"Изогнутое", "Стальное", "Старое",
            "Грязное", "Запачканное", "Сломанное",
            "Тупое", "Поганое", "Грубое", "Гнилое", "Скверное",
            "Зловонное"};

    String[] rareNounsIt = {"Морозное", "Тяжелое", "Лютое",
            "Славянское", "Арабское", "Гигантское", "Огромное",
            "Брутальное", "Православное", "Мусульманское", "Острое",
            "Шипастое", "Славное",
            };

    String[] epicNounsIt = {"Древнее", "Всемогущее", "Истинное",
            "Полуночное",
            "Чудовищное",
            "Восхитительное",
            "Святое", "Божественное"};



    String[] nounsWhomM = {"ехидного", "чумного", "мощного", "чудного", "придурковатого", "старого", "пыльного",
            "бешенного", "славянского", "арабского", "огромного", "шустрого", "безрассудного", "смазливого",
            "чудовищного", "опасного", "католического", "нервного", "хромого", "толстого", "неряшливого",
            "тупого", "придурковатого", "удалого", "богатого", "непристойного", "смелого", "напыщенного",
            "кровожадного", "честного", "хитрожопого", "жадного", "свирепого", "слепого", "сутулого", "мёрвтого",
            "патлатого", "косматого", "горластого", "окоченевшего", "обезумевшего", "аморального", "мёртвого" };

    String[] nounsWhomW = {"ревущей", "продажной", "скудоумной", "тощей", "безрассудной", "дряхлой", "вредной",
            "хитрой", "беззубой", "страшной", "прекрасной", "жадной", "страстной", "восточной", "стервозной",
            "проклятой", "подлой", "щедрой", "мёртвой", "скромной", "ласковой", "одинокой",
            "заразной", "распутной", "славянской", "арабской", "чудовищной", "гадкой", "мрачной"};

    String[] whomM = {"офицера", "нищего", "ублюдка", "матроса", "викинга", "чудовища",
            "лейтинанта", "судьи", "убийцы", "чёрта", "узурпатора", "вояки", "лешего", "шакала", "горбуна",
            "демона", "беса"};

    String[] whomW = {"смерти", "вдовы", "чертовщины",  "крысы", "ведьмы", "колдуньи",
            "старухи", "крестьянки", "монахини", "барышни"};

    String[] characteristics = {"слабости", "бешенства", "скромности", "правды", "памяти", "ловкости", "мудрости",
            "силы", "истины", "меткости", "желания"};

    public Item chestOpen() {
        int randomNum = Tools.getRandomNumber(1, 19);
        String name = "Пусто";
        int price = 0;
        switch (randomNum) {
            case (1)://обыч. прил муж + оружие муж
                name = usuallyWeaponNounsM[(int) (Math.random() * (usuallyWeaponNounsM.length - 1))] +
                        " " + weaponsM[(int) (Math.random() * (weaponsM.length - 1))];
                price = 0;
                break;
            case (2)://редкое прил муж + оружие муж
                name = rareWeaponNounsM[(int) (Math.random() * (rareWeaponNounsM.length - 1))] +
                        " " + weaponsM[(int) (Math.random() * (weaponsM.length - 1))];
                price = 1;
                break;
            case (3)://эпич. прил муж + оружие муж
                name = epicWeaponNounsM[(int) (Math.random() * (epicWeaponNounsM.length - 1))] +
                        " " + weaponsM[(int) (Math.random() * (weaponsM.length - 1))];
                price = 2;
                break;
            case (4)://обыч. прил жен + оружие жен
                name = usuallyWeaponNounsW[(int) (Math.random() * (usuallyWeaponNounsW.length - 1))] +
                        " " + weaponsW[(int) (Math.random() * (weaponsW.length - 1))];
                price = 0;
                break;
            case (5)://ред. прил жен + оружие жен
                name = rareWeaponNounsW[(int) (Math.random() * (rareWeaponNounsW.length - 1))] +
                        " " + weaponsW[(int) (Math.random() * (weaponsW.length - 1))];
                price = 1;
                break;
            case (6)://эпич. прил жен + оружие жен
                name = epicWeaponNounsW[(int) (Math.random() * (epicWeaponNounsW.length - 1))] +
                        " " + weaponsW[(int) (Math.random() * (weaponsW.length - 1))];
                price = 2;
                break;
            case (7)://обыч. прил. сред + оружие сред
                name = usuallyNounsIt[(int) (Math.random() * (usuallyNounsIt.length - 1))] +
                        " " + weaponsIt[(int) (Math.random() * (weaponsIt.length - 1))];
                price = 0;
                break;
            case (8)://ред. прил. сред + оружие сред
                name = rareNounsIt[(int) (Math.random() * (rareNounsIt.length - 1))] +
                        " " + weaponsIt[(int) (Math.random() * (weaponsIt.length - 1))];
                price = 1;
                break;
            case (9)://эпич. прил. сред + оружие сред
                name = epicNounsIt[(int) (Math.random() * (epicNounsIt.length - 1))] +
                        " " + weaponsIt[(int) (Math.random() * (weaponsIt.length - 1))];
                price = 2;
                break;
            case (10)://обыч. прил муж + оружие муж + характеристика
                name = usuallyWeaponNounsM[(int) (Math.random() * (usuallyWeaponNounsM.length - 1))] +
                        " " + weaponsM[(int) (Math.random() * (weaponsM.length - 1))]  +
                        " " + characteristics[(int) (Math.random() * (characteristics.length - 1))];
                price = 0;
                break;
            case (11)://ред. прил муж + оружие муж + характеристика
                name = rareWeaponNounsM[(int) (Math.random() * (rareWeaponNounsM.length - 1))] +
                        " " + weaponsM[(int) (Math.random() * (weaponsM.length - 1))]  +
                        " " + characteristics[(int) (Math.random() * (characteristics.length - 1))];
                price = 1;
                break;
            case (12)://эпич. прил муж + оружие муж + характеристика
                name = epicWeaponNounsM[(int) (Math.random() * (epicWeaponNounsM.length - 1))] +
                        " " + weaponsM[(int) (Math.random() * (weaponsM.length - 1))]  +
                        " " + characteristics[(int) (Math.random() * (characteristics.length - 1))];
                price = 2;
                break;
            case (13)://обыч. прил жен + оружие жен + характеристика
                name = usuallyWeaponNounsW[(int) (Math.random() * (usuallyWeaponNounsW.length - 1))] +
                        " " + weaponsW[(int) (Math.random() * (weaponsW.length - 1))]+
                        " " + characteristics[(int) (Math.random() * (characteristics.length - 1))];
                price = 0;
                break;
            case (14)://ред. прил жен + оружие жен + характеристика
                name = rareWeaponNounsW[(int) (Math.random() * (rareWeaponNounsW.length - 1))] +
                        " " + weaponsW[(int) (Math.random() * (weaponsW.length - 1))]+
                        " " + characteristics[(int) (Math.random() * (characteristics.length - 1))];
                price = 1;
                break;
            case (15)://эпич. прил жен + оружие жен + характеристика
                name = epicWeaponNounsW[(int) (Math.random() * (epicWeaponNounsW.length - 1))] +
                        " " + weaponsW[(int) (Math.random() * (weaponsW.length - 1))]+
                        " " + characteristics[(int) (Math.random() * (characteristics.length - 1))];
                price = 2;
                break;
            case (16)://прил сред + оружие сред + характеристика
                name = rareNounsIt[(int) (Math.random() * (rareNounsIt.length - 1))] +
                        " " + weaponsIt[(int) (Math.random() * (weaponsIt.length - 1))]+
                        " " + characteristics[(int) (Math.random() * (characteristics.length - 1))];
                price = 1;
                break;
            case (17)://прил муж + оружие муж + кого прилаг муж + чьё муж
                name = usuallyWeaponNounsM[(int) (Math.random() * (usuallyWeaponNounsM.length - 1))] +
                        " " + weaponsM[(int) (Math.random() * (weaponsM.length - 1))]  +
                        " " + nounsWhomM[(int) (Math.random() * (nounsWhomM.length - 1))] +
                        " " + whomM[(int) (Math.random() * (whomM.length - 1))];
                price = 0;
                break;
            case (18)://прил жен + оружие жен + кого прилаг жен + чьё жен
                name = usuallyWeaponNounsW[(int) (Math.random() * (usuallyWeaponNounsW.length - 1))] +
                        " " + weaponsW[(int) (Math.random() * (weaponsW.length - 1))]  +
                        " " + nounsWhomW[(int) (Math.random() * (nounsWhomW.length - 1))] +
                        " " + whomW[(int) (Math.random() * (whomW.length - 1))];
                price = 0;
                break;
            case (19)://прил муж + мусор муж + кого прилаг муж + чьё муж
                name = trashNounsM[(int) (Math.random() * (trashNounsM.length - 1))] +
                        " " + trashM[(int) (Math.random() * (trashM.length - 1))]  +
                        " " + nounsWhomM[(int) (Math.random() * (nounsWhomM.length - 1))] +
                        " " + whomM[(int) (Math.random() * (whomM.length - 1))];
                price = -1;
                break;
            case (20)://хор. прил жен + мусор жен + кого прилаг жен + чьё жен
                name = goodTrashNounsW[(int) (Math.random() * (goodTrashNounsW.length - 1))] +
                        " " + trashW[(int) (Math.random() * (trashW.length - 1))]  +
                        " " + nounsWhomW[(int) (Math.random() * (nounsWhomW.length - 1))] +
                        " " + whomW[(int) (Math.random() * (whomW.length - 1))];
                price = 1;
                break;
            case (21)://хор. прил жен + мусор жен + кого прилаг жен + чьё жен
                name = badTrashNounsW[(int) (Math.random() * (badTrashNounsW.length - 1))] +
                        " " + trashW[(int) (Math.random() * (trashW.length - 1))]  +
                        " " + nounsWhomW[(int) (Math.random() * (nounsWhomW.length - 1))] +
                        " " + whomW[(int) (Math.random() * (whomW.length - 1))];
                price = -1;
                break;
            case (22)://прил сред + мусор сред
                name = rareNounsIt[(int) (Math.random() * (rareNounsIt.length - 1))] +
                        " " + trashIt[(int) (Math.random() * (trashIt.length - 1))];
                price = 1;
                break;
            case (23)://прил сред + мусор сред
                name = usuallyNounsIt[(int) (Math.random() * (usuallyNounsIt.length - 1))] +
                        " " + trashIt[(int) (Math.random() * (trashIt.length - 1))];
                price = 0;
                break;

        }
        return fabricaOfItem(name, price);
    }

    public Item fabricaOfItem(String name, int price){
        Item item = null;
        if (price <= 0){
            item = new UsuallyItem(name, price);
        }
        if (price == 1){
            item = new RareItem(name, price);
        }
        if (price > 1){
            item = new EpicItem(name, price);
        }
        return item;
    }

    public class Item extends Text {
        String name;
        int price;

        public Item(String name, int price){
            this.name = Tools.textFormatter(name);
            this.price = price;
            setText(this.name);
            setFont(new Font(20));
        }

        @Override
        public String toString() {
            return name;
        }
    }

    private class UsuallyItem extends Item{

        public UsuallyItem(String name, int price) {
            super(name, price);
            setFill(Color.GAINSBORO);
        }
    }
    private class RareItem extends Item{

        public RareItem(String name, int price) {
            super(name, price);
            setFill(Color.BLUE);
        }
    }
    private class EpicItem extends Item{

        EpicItem(String name, int price){
            super(name, price);
            setFill(Color.ORANGE);
        }
    }
}
