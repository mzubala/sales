package pl.com.bottega.play;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="document")
public class DocumentHeavy extends DocumentBase {

    private String title, content;

}
