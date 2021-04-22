package com.core;

import javafx.fxml.FXML;

import java.util.ArrayList;

public class Chest {
    private ArrayList<String> items;


    public ArrayList<String> getItems() {
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
            "Клевец", "Чекан", "Кончар", "Эсток", "Стилет", "Багор", "Гарпун", "Дротик", "Пилум", "Джавелин", "Джерид",
            "Полэкс", "Томагавк", "Крис", "Двуручный меч", "Крепкий"};
    String[] weaponsW = {"Булава", "Секира", "Сабля", "Алебарда", "Дубина", "Рогатина", "Праща", "Шпага",
            "Нагамаки", "Палица", "Нунчака", "Шалапуга", "Макила", "Палка", "Кама",
            "Кусаригама", "Рапира", "Пика", "Острога", "Айболта", "Валашка", "Коса"};
    String[] weaponsIt = {"Копьё", "Зубило", "Дробило", "Мачете", "Хоко", "Гасило"};

    String[] trashM = {"канат", "якорь", "амулет", "компас", "плащ", "глобус", "рюкзак", "сапог", "кувшин", "факел",
                        "подсвечник", "флаг", "пуд соли", "мундир", "мешок с песком", "мещок яблок", "мешок картошки",
                        "мешок с дерьмом", "табак", "камзол",  "таз", "портрет", "ром", "костыль",  "виски",};

    String[] trashW = {"чаша", "трубка", "сеть для ловли", "рубаха", "кастрюля", "шуба", "лодка", "цепочка", "трость",
                        "мантия", "рыбка", "бочка рома", "бочка картошки", "табакерка", "водка", "клешня", "шкатулка"};

    String[] trashIt = {"Ожерелье", "Пушечное ядро", "Ведро", "Платье", "Веретено", "Весло", "Авокадо", "Знамя",
                        "Вино", "коромысло", "седло"};

    String[] weaponNounsM = {"Холодный", "Неплохой", "Изящный", "Алый", "Вечный", "Пыльный",
            "Утренний", "Бешеный", "Славянский", "Арабский", "Жалкий", "Гигантский", "Огромный",
            "Чудовищный", "Гибельный", "Православный", "Мусульманский", "Острый",
            "Тупой", "Смешной", "Лихой", "Чудесный", "Богатый", "Расписной", "Ужасный", "Непристойный",
            "Кровожадный", "Священный", "Идеальный"};//разное

    String[] weaponNounsW = {"Ледяная", "Пригожая", "Изящная", "Кровавая", "Уродливая", "Кривая",
            "Ночная", "Яростная", "Славянская", "Арабская", "Ладная", "Гигантская", "Огромная",
            "Чудовищная", "Смертельная", "Православная", "Мусульманская", "Острая",
            "Тупая", "Злющая", "Колючая", "Призрачная", "Роскошная", "Расписная", "Извращенная",
            "Дьявольская", "Сумрачная", "Божественная"};//разное

    String[] trashNounsM = {"рваный", "прочный", "ржавый", "тяжелый", "красивый", "драгоценный", "старинный",
                            "потёртый", "привлекательный", "надёжный", "чёрный", "кожаный", "треснувший", "дешёвый",
                            "крепкий", "брошенный", "пыльный", "расписной", "вонючий", "благоухающий", "славянский",
                            "арабский"};

    String[] trashNounsW = {"Изящная", "редкая", "раритетная", "корявая", "потёртая", "музыкальная", "драгоценная",
                            "ненужная", "выкинутая", "дешёвая", "роскошная", "ничтожная", "готичечская", "старинная",
                            "мокрая", "славянская", "арабская", "культурная", "хрупкая", "необычная", "божья"};

    String[] trashNounsIt = {};

    String[] nounsIt = {"Морозное", "Тяжелое", "Лютое", "Изогнутое", "Стальное", "Древнее", "Старое",
            "Полуночное", "Грязное", "Запачканное", "Славянское", "Арабское", "Сломанное", "Гигантское", "Огромное",
            "Чудовищное", "Брутальное", "Православное", "Мусульманское", "Острое",
            "Тупое", "Шипастое", "Славное", "Восхитительное", "Поганое", "Грубое", "Гнилое", "Скверное",
            "Зловонное", "Святое"};//разное

    String[] nounsWhomM = {"ехидного", "чумного", "мощного", "чудного", "придурковатого", "старого", "пыльного",
            "бешенного", "славянского", "арабского", "огромного", "шустрого", "безрассудного", "смазливого",
            "чудовищного", "опасного", "католического", "нервного", "потного", "толстого", "неряшливого",
            "тупого", "придурковатого", "удалого", "богатого", "непристойного", "смелого", "напыщенного",
            "кровожадного", "честного", "хитрожопого", "жадного", "свирепого", "слепого", "сутулого", "мёрвтого",
            "патлатого", "косматого", "горластого", "окоченевшего", "обезумевшего", "аморального", "мёртвого" };

    String[] nounsWhomW = {"ревущей", "продажной", "скудоумной", "тощей", "безрассудной", "дряхлой", "вредной",
            "хитрой", "беззубой", "страшной", "прекрасной", "жадной", "страстной", "восточной", "стервозной",
            "проклятой", "подлой", "щедрой", "мёртвой", "скромной", "ласковой", "одинокой",
            "заразной", "распутной", "слдавянской", "арабской", "чудовищной", "гадкой", "мрачной"};

    String[] whomM = {"офицера", "нищего", "ублюдка", "матроса", "викинга", "чудовища",
            "лейтинанта", "судьи", "убийцы", "чёрта", "узурпатора", "вояки", "лешего", "шакала", "горбуна",
            "демона", "беса"};

    String[] whomW = {"смерти", "бабки", "вдовы", "чертовщины",  "крысы", "ведьмы", "колдуньи",
            "старухи", "суки", "крестьянки", "монахини", "барышни"};

    String[] characteristics = {"слабости", "бешенства", "скромности", "правды", "памяти", "ловкости", "мудрости",
            "силы", "истины", "меткости", "желания"};

    public String chestOpen() {
        int randomNum = 1 + (int) (Math.random() * 11);
        String name = "Пусто";
        switch (randomNum) {
            case (1)://прил муж + оружие муж
                name = weaponNounsM[(int) (Math.random() * (weaponNounsM.length - 1))] +
                        " " + weaponsM[(int) (Math.random() * (weaponsM.length - 1))];
                break;
            case (2)://прил жен + оружие жен
                name = weaponNounsW[(int) (Math.random() * (weaponNounsW.length - 1))] +
                        " " + weaponsW[(int) (Math.random() * (weaponsW.length - 1))];
                break;
            case (3)://прил сред + оружие сред
                name = nounsIt[(int) (Math.random() * (nounsIt.length - 1))] +
                        " " + weaponsIt[(int) (Math.random() * (weaponsIt.length - 1))];
                break;
            case (4)://прил муж + оружие муж + характеристика
                name = weaponNounsM[(int) (Math.random() * (weaponNounsM.length - 1))] +
                        " " + weaponsM[(int) (Math.random() * (weaponsM.length - 1))]  +
                        " " + characteristics[(int) (Math.random() * (characteristics.length - 1))];
                break;
            case (5)://прил жен + оружие жен + характеристика
                name = weaponNounsW[(int) (Math.random() * (weaponNounsW.length - 1))] +
                        " " + weaponsW[(int) (Math.random() * (weaponsW.length - 1))]+
                        " " + characteristics[(int) (Math.random() * (characteristics.length - 1))];
                break;
            case (6)://прил сред + оружие сред + характеристика
                name = nounsIt[(int) (Math.random() * (nounsIt.length - 1))] +
                        " " + weaponsIt[(int) (Math.random() * (weaponsIt.length - 1))]+
                        " " + characteristics[(int) (Math.random() * (characteristics.length - 1))];
                break;
            case (7)://прил муж + оружие муж + кого прилаг муж + чьё муж
                name = weaponNounsM[(int) (Math.random() * (weaponNounsM.length - 1))] +
                        " " + weaponsM[(int) (Math.random() * (weaponsM.length - 1))]  +
                        " " + nounsWhomM[(int) (Math.random() * (nounsWhomM.length - 1))] +
                        " " + whomM[(int) (Math.random() * (whomM.length - 1))];
                break;
            case (8)://прил жен + оружие жен + кого прилаг жен + чьё жен
                name = weaponNounsW[(int) (Math.random() * (weaponNounsW.length - 1))] +
                        " " + weaponsW[(int) (Math.random() * (weaponsW.length - 1))]  +
                        " " + nounsWhomW[(int) (Math.random() * (nounsWhomW.length - 1))] +
                        " " + whomW[(int) (Math.random() * (whomW.length - 1))];
                break;
            case (9)://прил муж + мусор муж + кого прилаг муж + чьё муж
                name = trashNounsM[(int) (Math.random() * (trashNounsM.length - 1))] +
                        " " + trashM[(int) (Math.random() * (trashM.length - 1))]  +
                        " " + nounsWhomM[(int) (Math.random() * (nounsWhomM.length - 1))] +
                        " " + whomM[(int) (Math.random() * (whomM.length - 1))];
                break;
            case (10)://прил жен + мусор жен + кого прилаг жен + чьё жен
                name = trashNounsW[(int) (Math.random() * (trashNounsW.length - 1))] +
                        " " + trashW[(int) (Math.random() * (trashW.length - 1))]  +
                        " " + nounsWhomW[(int) (Math.random() * (nounsWhomW.length - 1))] +
                        " " + whomW[(int) (Math.random() * (whomW.length - 1))];
                break;
            case (11)://прил сред + мусор сред
                name = nounsIt[(int) (Math.random() * (nounsIt.length - 1))] +
                        " " + trashIt[(int) (Math.random() * (trashIt.length - 1))];
                break;

        }
        return name;
    }

    private class usuallyItem {

    }
    private class rareItem {

    }
    private class epicItem {

    }
}
