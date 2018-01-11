package ru.firemoon777.studentcardreader;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dragon on 11.01.2018.
 */

public class StudentCardParser {

    private static String decodeCardType(String cardType) {
        if("173B".equals(cardType)) {
            return "Полный БСК";
        } else if("2302".equals(cardType)) {
            return "Только метро";
        } else if("5302".equals(cardType)) {
            return "Полный БСК";
        }
        return "Неизвестный БСК";
    }

    public static String routeParser(String r) {
        switch (r) {
            case "4300":
                return "Троллейбус 1";
            case "7900":
                return "Автобус 21";
            case "9900":
                return "Трамвай 6";
            case "1D3B":
                return "Автобус 24";
            case "8E01":
                return "Автобус 27";
            case "9E3B":
                return "Автобус 153";
            default:
                return "Хз ваще";
        }
    }

    public static String stationParser(byte n) {
        int number = (int)n & 0xFF;
        switch (number) {
            case 0x00:
                return "Спортивная";
            case 0x31:
                return "Площадь Александра Невского";
            case 0x6C:
                return "Балтийская";
            case 0x6D:
                return "Пушкинская";
            case 0x6E:
                return "Достоевская";
            case 0x6F:
                return "Площадь Восстания";
            case 0x71:
                return "Чернышевская";
            case 0x72:
                return "Площадь Ленина";
            case 0x75:
                return "Лесная";
            case 0x7A:
                return "Девяткино";
            case 0x93:
                return "Проспект Большевиков";
            case 0x94:
                return "Ладожская";
            case 0x95:
                return "Новочеркасская";
            case 0x97:
                return "Лиговский проспект";
            case 0xCD:
                return "Парк Победы";
            case 0xD1:
                return "Технологический институт";
            case 0xD3:
                return "Сенная площадь";
            case 0xD4:
                return "Невский проспект";
            case 0xD6:
                return "Горьковская";
            case 0xD7:
                return "Петрога";
            case 0xF9:
                return "Международная";
            default:
                return "Не знаю :C";
        }
    }

    public static List<StudentCardDataRow> parseStudentCardData(Context context, StudentCardData data) {
        List<StudentCardDataRow> result = new ArrayList<>();
        if(data == null) {
            return result;
        }
        StudentCardDataRow row;

        /* Информация о карте */
        row = new StudentCardDataRow(context.getString(R.string.card_data_general));
        result.add(row);

        row = new StudentCardDataRow(context.getString(R.string.card_data_valid_from), data.getValidFrom());
        result.add(row);

        row = new StudentCardDataRow(context.getString(R.string.card_data_update), data.getUpdateTime());
        result.add(row);

        row = new StudentCardDataRow(context.getString(R.string.card_data_valid_until), data.getValidUntil());
        result.add(row);

        row = new StudentCardDataRow(
                context.getString(R.string.card_data_type_card),
                decodeCardType(data.getCardType()),
                data.getCardType());
        result.add(row);

        /* Информация о поездках в метро */
        row = new StudentCardDataRow(context.getString(R.string.card_data_metro));
        result.add(row);

        row = new StudentCardDataRow(context.getString(R.string.card_data_metro_time), data.getMetroTime());
        result.add(row);

        row = new StudentCardDataRow(context.getString(R.string.card_data_station));
        row.setReleaseValue(stationParser(data.getStation()));
        row.setDebugValue(String.format("%02x / %02x", (data.getStation().intValue() & 0xFF), (data.getStationAdditional().intValue() & 0xFF)).toUpperCase());
        result.add(row);

        row = new StudentCardDataRow(context.getString(R.string.card_data_entrance), data.getEntrance().toString());
        result.add(row);

        /* Информация о поездках на наземке*/
        row = new StudentCardDataRow(context.getString(R.string.card_data_ground));
        result.add(row);

        row = new StudentCardDataRow(context.getString(R.string.card_data_ground_time), data.getGroundTime());
        result.add(row);

        row = new StudentCardDataRow(context.getString(R.string.card_data_route), routeParser(data.getRoute()), data.getRoute());
        result.add(row);

        /* Персональные данные */
        row = new StudentCardDataRow(context.getString(R.string.card_data_personal));
        result.add(row);

        row = new StudentCardDataRow(context.getString(R.string.card_data_name), data.getSurname() + " " + data.getFirstName());
        row.setPersonal(true);
        result.add(row);

        row = new StudentCardDataRow(context.getString(R.string.card_data_passport), data.getPassport());
        row.setPersonal(true);
        result.add(row);

        return result;
    }
}
