package pl.com.bottega.play;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="document")
public class DocumentLite extends DocumentBase {

    private String title;

}
