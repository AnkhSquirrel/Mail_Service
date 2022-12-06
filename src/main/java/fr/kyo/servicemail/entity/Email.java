package fr.kyo.servicemail.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Email {
    String to;
    String header;
    String body;
}
