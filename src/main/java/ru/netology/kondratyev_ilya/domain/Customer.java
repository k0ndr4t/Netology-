package ru.netology.kondratyev_ilya.domain;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Customer implements ConsolePrintable {
    int id;
    String name;

    @Override
    public void printToConsole(){
        System.out.println(id + name);
    }
}
