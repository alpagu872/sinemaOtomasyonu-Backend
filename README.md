### Sinema Otomasyon Sistemi - README

#### Proje Hakkında
Bu proje, bir **Sinema Otomasyon Sistemi** geliştirilmesine yöneliktir. Sistem, sinema seanslarını planlama, bilet rezervasyonu yapma ve kullanıcı yönetimi gibi işlevleri içerir. Kullanıcıların kayıt olup giriş yapabileceği, yetkilendirilmiş işlemleri gerçekleştirebileceği, bilet alabileceği ve seansları sorgulayabileceği bir API sunmaktadır. Proje **Spring Boot**, **Spring Security**, **JWT** ve **Spring Data JPA** kullanılarak geliştirilmiştir.

---

### İçindekiler
1. [Özellikler](#özellikler)
2. [Kullanılan Teknolojiler](#kullanılan-teknolojiler)
3. [Proje Kurulumu](#proje-kurulumu)
4. [API Endpoints](#api-endpoints)
   - Kullanıcı Yönetimi
   - Diğerleri
5. [JWT Kimlik Doğrulama](#jwt-kimlik-doğrulama)
6. [Hata Yönetimi](#hata-yonetimi)
7. [Swagger Dokümantasyonu](#swagger-dokümantasyonu)

---

### Özellikler
- **Kullanıcı Kayıt ve Giriş:** Kullanıcıların kayıt olup sisteme giriş yapabileceği güvenli bir API.
- **JWT Kimlik Doğrulama ve Yetkilendirme:** Tüm yetkilendirilmiş işlemler JWT ile korunmaktadır.
- **Bilet Rezervasyonu:** Kullanıcıların sinema biletlerini online olarak alabileceği ve iptal edebileceği bir yapı.
- **Seans Yönetimi:** Seanslar üzerinde yönetim (ekleme, güncelleme) işlemleri.
- **Veritabanı Yönetimi:** Kullanıcı, seans ve bilet bilgilerinin veritabanında tutulması ve işlemler için uygun repository yapısı.
- **Hata Yönetimi:** Merkezi hata yönetimi ile anlaşılır hata mesajları.

---

### Kullanılan Teknolojiler
- **Java 17**
- **Spring Boot 3.3**
- **Spring Security**
- **JWT (JSON Web Token)**
- **Spring Data JPA** (Hibernate)
- **PostgreSQL** (Veritabanı)
- **Swagger** (API dokümantasyonu için)
- **Lombok** (Kod sadeleştirme)

---

### Proje Kurulumu

#### Gereksinimler:
- **Java 17** veya daha yeni bir sürüm.
- **Maven** (Proje bağımlılıklarını yönetmek için).
- **PostgreSQL** veritabanı.

#### Kurulum Adımları:
1. **Projeyi İndir:** 
    ```
    git clone https://github.com/kullanici/sinema-otomasyon.git
    cd sinema-otomasyon
    ```

2. **Veritabanı Bağlantısını Yapılandırın:**
   - `src/main/resources/application.properties` dosyasındaki PostgreSQL ayarlarını kendi veritabanınıza göre düzenleyin:
     ```properties
     spring.datasource.url=jdbc:postgresql://localhost:5432/sinema_db
     spring.datasource.username=postgres
     spring.datasource.password=yourpassword
     spring.jpa.hibernate.ddl-auto=update
     spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
     ```

3. **Projeyi Derleyin ve Çalıştırın:**
    ```
    mvn clean install
    mvn spring-boot:run
    ```

4. **Swagger Dokümantasyonuna Erişim:**
   - Proje çalıştıktan sonra **Swagger** API dokümantasyonuna şu adresten erişebilirsiniz:
   ```
   http://localhost:8080/swagger-ui.html
   ```

---

### API Endpoints

### API Endpoints

#### Kullanıcı Yönetimi (User Controller)

| HTTP Method | URL                         | Açıklama                                      | Authorization |
|-------------|-----------------------------|-----------------------------------------------|---------------|
| PUT         | `/api/users/update/{id}`     | Belirtilen kullanıcıyı günceller.             | Admin         |
| POST        | `/api/users`                 | Yeni bir kullanıcı oluşturur.                 | Admin         |
| GET         | `/api/users/getById/{id}`    | Belirtilen kullanıcıyı getirir.               | Admin         |
| GET         | `/api/users/getAll`          | Tüm kullanıcıları listeler.                   | Admin         |
| DELETE      | `/api/users/delete/{id}`     | Belirtilen kullanıcıyı siler.                 | Admin         |

#### Bilet Yönetimi (Ticket Controller)

| HTTP Method | URL                         | Açıklama                                      | Authorization |
|-------------|-----------------------------|-----------------------------------------------|---------------|
| GET         | `/api/tickets/{id}`          | Belirtilen bileti getirir.                    | User/Admin    |
| PUT         | `/api/tickets/{id}`          | Belirtilen bileti günceller.                  | User/Admin    |
| DELETE      | `/api/tickets/{id}`          | Belirtilen bileti siler.                      | Admin         |
| POST        | `/api/tickets`               | Yeni bir bilet oluşturur.                     | User/Admin    |
| GET         | `/api/tickets/getAll`        | Tüm biletleri listeler.                       | Admin         |

#### Tiyatro Yönetimi (Theatre Controller)

| HTTP Method | URL                         | Açıklama                                      | Authorization |
|-------------|-----------------------------|-----------------------------------------------|---------------|
| GET         | `/api/theatres/{id}`         | Belirtilen tiyatroyu getirir.                 | Public        |
| PUT         | `/api/theatres/{id}`         | Belirtilen tiyatroyu günceller.               | Admin         |
| DELETE      | `/api/theatres/{id}`         | Belirtilen tiyatroyu siler.                   | Admin         |
| POST        | `/api/theatres`              | Yeni bir tiyatro oluşturur.                   | Admin         |
| GET         | `/api/theatres/getAll`       | Tüm tiyatroları listeler.                     | Public        |

#### Gösteri Yönetimi (Show Controller)

| HTTP Method | URL                                         | Açıklama                                      | Authorization |
|-------------|---------------------------------------------|-----------------------------------------------|---------------|
| GET         | `/api/shows/{id}`                           | Belirtilen gösteriyi getirir.                 | Public        |
| PUT         | `/api/shows/{id}`                           | Belirtilen gösteriyi günceller.               | Admin         |
| DELETE      | `/api/shows/{id}`                           | Belirtilen gösteriyi siler.                   | Admin         |
| POST        | `/api/shows`                                | Yeni bir gösteri oluşturur.                   | Admin         |
| GET         | `/api/shows/getByMovieId/{movieId}`         | Belirtilen film için gösterileri getirir.     | Public        |
| GET         | `/api/shows/getAll`                         | Tüm gösterileri listeler.                     | Public        |

#### Salon Yönetimi (Screen Controller)

| HTTP Method | URL                         | Açıklama                                      | Authorization |
|-------------|-----------------------------|-----------------------------------------------|---------------|
| GET         | `/api/screens/{id}`          | Belirtilen salonu getirir.                    | Public        |
| PUT         | `/api/screens/{id}`          | Belirtilen salonu günceller.                  | Admin         |
| DELETE      | `/api/screens/{id}`          | Belirtilen salonu siler.                      | Admin         |
| POST        | `/api/screens`               | Yeni bir salon oluşturur.                     | Admin         |
| GET         | `/api/screens/getAll`        | Tüm salonları listeler.                       | Public        |

#### Film Yönetimi (Movie Controller)

| HTTP Method | URL                         | Açıklama                                      | Authorization |
|-------------|-----------------------------|-----------------------------------------------|---------------|
| GET         | `/api/movies/{id}`           | Belirtilen filmi getirir.                     | Public        |
| PUT         | `/api/movies/{id}`           | Belirtilen filmi günceller.                   | Admin         |
| DELETE      | `/api/movies/{id}`           | Belirtilen filmi siler.                       | Admin         |
| POST        | `/api/movies`                | Yeni bir film oluşturur.                      | Admin         |
| GET         | `/api/movies/getAll`         | Tüm filmleri listeler.                        | Public        |

#### Rezervasyon Yönetimi (Booking Controller)

| HTTP Method | URL                         | Açıklama                                      | Authorization |
|-------------|-----------------------------|-----------------------------------------------|---------------|
| GET         | `/api/bookings/{id}`         | Belirtilen rezervasyonu getirir.              | User/Admin    |
| PUT         | `/api/bookings/{id}`         | Belirtilen rezervasyonu günceller.            | User/Admin    |
| DELETE      | `/api/bookings/{id}`         | Belirtilen rezervasyonu siler.                | User/Admin    |
| POST        | `/api/bookings`              | Yeni bir rezervasyon oluşturur.               | User/Admin    |
| GET         | `/api/bookings/getAll`       | Tüm rezervasyonları listeler.                 | Admin         |

#### Kimlik Doğrulama Yönetimi (Authentication Controller)

| HTTP Method | URL                         | Açıklama                                      | Authorization |
|-------------|-----------------------------|-----------------------------------------------|---------------|
| POST        | `/api/register`              | Yeni kullanıcı kaydeder.                      | Public        |
| POST        | `/api/authenticate`          | Giriş yapar ve JWT döner.                     | Public        |

#### İstisna Yönetimi (Exception Controller)

| HTTP Method | URL                                       | Açıklama                                      | Authorization |
|-------------|-------------------------------------------|-----------------------------------------------|---------------|
| GET         | `/api/exceptions/unhandled-exception`      | İşlenmemiş istisnaları döner.                 | Public        |
| GET         | `/api/exceptions/resource-not-found`       | Kaynak bulunamadı istisnası döner.            | Public        |
| GET         | `/api/exceptions/invalid-input`            | Geçersiz giriş istisnası döner.               | Public        |
| GET         | `/api/exceptions/illegal-argument`         | Geçersiz argüman istisnası döner.             | Public        |
| GET         | `/api/exceptions/access-denied`            | Erişim reddedildi istisnası döner.            | Public        |

### JWT Kimlik Doğrulama
Kullanıcılar oturum açtıklarında bir JWT (**JSON Web Token**) oluşturulur ve bu token, sunucuya yapılan her talepte kimlik doğrulama için kullanılır. Token doğrulaması sırasında, `JwtRequestFilter` kullanılarak her istek doğrulanır.

- **Oturum Açma:** `/api/authenticate` üzerinden `phoneNumber` ve `password` bilgilerini POST ederek JWT token alınır.
- **Token Kullanımı:** Token, isteklerde `Authorization` başlığı altında gönderilir.
    ```
    Authorization: Bearer <token>
    ```

---





### Hata Yönetimi
Projede merkezi hata yönetimi **@ControllerAdvice** ve **GlobalExceptionHandler** ile sağlanmaktadır. Herhangi bir hata durumunda anlaşılır ve detaylı hata mesajları döndürülmektedir.

- **401 Unauthorized:** JWT token geçersiz ya da eksik olduğunda döner.
- **409 Conflict:** Kullanıcı kaydı sırasında zaten mevcut olan bir telefon numarası girildiğinde döner.

---

### Swagger Dokümantasyonu
Projedeki API'leri ve işlevleri detaylı olarak incelemek için **Swagger** dokümantasyonunu kullanabilirsiniz. Swagger arayüzüne şu URL üzerinden ulaşabilirsiniz:
```
http://localhost:8080/swagger-ui.html
```

---



