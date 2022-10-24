package vn.unicloud.umeepay.entity.merchant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Table(name = IdentifyCard.COLLECTION_NAME)
public class IdentifyCard {

    public static final String COLLECTION_NAME = "identity_card";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String frontUrl;

    private String backUrl;

 }
