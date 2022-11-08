package vn.unicloud.umeepay.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.unicloud.umeepay.enums.DocumentType;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@Table(name = Document.COLLECTION_NAME)
@AllArgsConstructor
@NoArgsConstructor
public class Document {

    public static final String COLLECTION_NAME = "document";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;

    @Enumerated(EnumType.STRING)
    private DocumentType type;

    private String url;

    @Override
    public String toString() {
        return "Document{" +
                "id=" + id +
                "fileName=" + fileName +
                ", type=" + type +
                ", url='" + url + '\'' +
                '}';
    }
}
