package com.auctionshortenedurl.model.url;

import com.auctionshortenedurl.model.user.User;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "url", uniqueConstraints = {@UniqueConstraint(columnNames = "url_id")})
public class Url {

    @Id
    @Column(name = "url_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long urlId;

    @Column(name = "long_url", nullable = false)
    private String longUrl;

    @Column(name = "short_url")
    private String shortUrl;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
