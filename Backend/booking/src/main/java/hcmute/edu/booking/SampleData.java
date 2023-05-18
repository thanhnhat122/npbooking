package hcmute.edu.booking;

import hcmute.edu.booking.model.*;
import hcmute.edu.booking.repository.*;
import hcmute.edu.booking.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SampleData {

    private static final Logger logger = LoggerFactory.getLogger(SampleData.class);

    @Bean
    CommandLineRunner initDatabase(UserService userService,
                                   ProvinceRepository provinceRepository,
                                   HotelRepository hotelRepository,
                                   RoomRepository roomRepository,
                                   ReviewRepository reviewRepository,
                                   ImageRepository imageRepository
    ) {

        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                User user = new User("nhat@gmail.com", "nhat123", "Nhat", "Thanh", "0869792657", User.RoleEnum.ROLE_AD, true);
                logger.info("insert data: " + userService.insertUser(user));

                User user1 = new User("long@gmail.com", "long123", "Long", "Hoang", "0934088208", User.RoleEnum.ROLE_NV, true);
                logger.info("insert data: " + userService.insertUser(user1));

                User user2 = new User("phuoc@gmail.com", "phuoc123", "Phuoc", "Thanh", "0934086546", User.RoleEnum.ROLE_KH, true);
                logger.info("insert data: " + userService.insertUser(user2));

                //Province
                Province pro1 = new Province("Đà Lạt", "https://res.cloudinary.com/dei0obeww/image/upload/v1671026917/npbooking/dalat_o2hbys.jpg", 123);
                Province pro2 = new Province("Đà Nẵng", "https://res.cloudinary.com/dei0obeww/image/upload/v1671027245/npbooking/danang_t8rlrk.jpg", 523);
                Province pro3 = new Province("Hội An", "https://res.cloudinary.com/dei0obeww/image/upload/v1671027264/npbooking/hoian_jekrey.jpg", 34);
                Province pro4 = new Province("Huế", "https://res.cloudinary.com/dei0obeww/image/upload/v1671027262/npbooking/hue_ppiluf.jpg", 76);
                Province pro5 = new Province("Phú Quốc", "https://res.cloudinary.com/dei0obeww/image/upload/v1671027259/npbooking/phuquoc_lxzhmi.jpg", 64);
                Province pro6 = new Province("TP. Hồ Chí Minh", "https://res.cloudinary.com/dei0obeww/image/upload/v1671027259/npbooking/tphochiminh_tlu4rl.jpg", 24);
                Province pro7 = new Province("Hà Nội", "https://res.cloudinary.com/dei0obeww/image/upload/v1671027258/npbooking/hanoi_dlhwde.jpg", 64);
                Province pro8 = new Province("Nha Trang", "https://res.cloudinary.com/dei0obeww/image/upload/v1671027262/npbooking/nhatrang_bxchjt.jpg", 245);

                logger.info("insert data: " + provinceRepository.save(pro1));
                logger.info("insert data: " + provinceRepository.save(pro2));
                logger.info("insert data: " + provinceRepository.save(pro3));
                logger.info("insert data: " + provinceRepository.save(pro4));
                logger.info("insert data: " + provinceRepository.save(pro5));
                logger.info("insert data: " + provinceRepository.save(pro6));
                logger.info("insert data: " + provinceRepository.save(pro7));
                logger.info("insert data: " + provinceRepository.save(pro8));


                //Hotel
                Hotel ho1 = new Hotel("Hai Trieu Hotel",
                        """
                                Tọa lạc tại thành phố Đà Nẵng, Hai Trieu Hotel có nhà hàng, hồ bơi ngoài trời, trung tâm thể dục và quầy bar. Chỗ nghỉ này có các phòng gia đình và sân hiên. Chỗ nghỉ cung cấp dịch vụ lễ tân 24 giờ, dịch vụ phòng và dịch vụ thu đổi ngoại tệ cho khách.

                                Các phòng nghỉ tại khách sạn được trang bị máy điều hòa, bàn làm việc, ấm đun nước, tủ lạnh, minibar, két an toàn, TV màn hình phẳng và phòng tắm riêng với vòi xịt/chậu rửa vệ sinh. Mỗi phòng nghỉ tại Hai Trieu Hotel đều được trang bị ga trải giường và khăn tắm.

                                Khách nghỉ tại khách sạn có thể thưởng thức bữa sáng buffet hoặc bữa sáng à la carte.

                                Hai Trieu Hotel cách Bãi biển Mỹ Khê 700 m và Cầu Sông Hàn 1,9 km. Sân bay gần nhất là sân bay quốc tế Đà Nẵng, cách đó 7 km, và khách sạn cung cấp dịch vụ đưa đón sân bay với một khoản phụ phí.""",
                        2,
                        "18 - 20 Loseby , Đà Nẵng, Việt Nam",
                        "pool,wifi,car-plane,gym,bar",
                        4,
                        true,
                        2.4,
                        true,
                        true,
                        "long@gmail.com");

                logger.info("insert data: " + hotelRepository.save(ho1));

                //Room
                Room room1_1 = new Room(1, "25 m²", 2_490_000.0, 2, 1_837_620.0, 0, 1, 8, 8, "Phòng Tiêu Chuẩn Giường Đôi","Căn hộ nguyên căn,Bếp riêng,Phòng tắm riêng trong phòng,Ban công,Nhìn ra thành phố,Hồ bơi có tầm nhìn,Hồ bơi trên sân thượng,Điều hòa không khí,TV màn hình phẳng,Hệ thống cách âm,Minibar,WiFi miễn phí",
                    "Đồ vệ sinh cá nhân miễn phí,Nhà vệ sinh,Bồn tắm,Vòi sen,Khăn tắm,Dép,Toilet chung,Máy sấy tóc,Toilet phụ,Giấy vệ sinh",
                    "Nhìn ra thành phố,Nhìn ra biển","Các tầng trên đi lên bằng thang máy,Các tầng trên chỉ lên được bằng cầu thang,Ra trải giường,Tủ hoặc phòng để quần áo,Căn hộ riêng trong tòa nhà,Nước rửa tay, Minibar,Điều hòa không khí, Sàn lát gỗ,Không gây dị ứng,Sàn lát gạch/đá cẩm thạch,Hệ thống cách âm,Bàn ủi, Quạt máy,Sàn trải thảm,Thiết bị báo carbon monoxide,Tủ lạnh,Lò vi sóng,Ấm đun nước điện,Bếp nấu,Đồ bếp,Bàn ăn,Bếp,Bếp nhỏ,Ghế sofa,Bàn làm việc,Khu vực tiếp khách,Khu vực phòng ăn,TV,TV màn hình phẳng,Truyền hình cáp,Ban công,Ổ điện gần giường,Giá treo quần áo,Giường xếp,Giá phơi quần áo,Giường sofa,Máy điều hòa độc lập cho từng phòng",
                    true);
                Room room1_2 = new Room(1, "25 m²", 2_490_000.0, 2, 1_837_620.0, 2, 0, 5, 5, "Phòng Superior 2 Giường Đơn","Căn hộ nguyên căn,Bếp riêng,Phòng tắm riêng trong phòng,Ban công,Nhìn ra thành phố,Hồ bơi có tầm nhìn,Hồ bơi trên sân thượng,Điều hòa không khí,TV màn hình phẳng,Hệ thống cách âm,Minibar,WiFi miễn phí",
                    "Đồ vệ sinh cá nhân miễn phí,Nhà vệ sinh,Bồn tắm,Vòi sen,Khăn tắm,Dép,Toilet chung,Máy sấy tóc,Toilet phụ,Giấy vệ sinh",
                    "Nhìn ra thành phố,Nhìn ra biển","Các tầng trên đi lên bằng thang máy,Các tầng trên chỉ lên được bằng cầu thang,Ra trải giường,Tủ hoặc phòng để quần áo,Căn hộ riêng trong tòa nhà,Nước rửa tay, Minibar,Điều hòa không khí, Sàn lát gỗ,Không gây dị ứng,Sàn lát gạch/đá cẩm thạch,Hệ thống cách âm,Bàn ủi, Quạt máy,Sàn trải thảm,Thiết bị báo carbon monoxide,Tủ lạnh,Lò vi sóng,Ấm đun nước điện,Bếp nấu,Đồ bếp,Bàn ăn,Bếp,Bếp nhỏ,Ghế sofa,Bàn làm việc,Khu vực tiếp khách,Khu vực phòng ăn,TV,TV màn hình phẳng,Truyền hình cáp,Ban công,Ổ điện gần giường,Giá treo quần áo,Giường xếp,Giá phơi quần áo,Giường sofa,Máy điều hòa độc lập cho từng phòng",
                    true);
                Room room1_3 = new Room(1, "28 m²", 2_600_000.0, 2, 1_918_800.0, 0, 1, 7, 7, "Phòng Deluxe Có Giường Cỡ King","Căn hộ nguyên căn,Bếp riêng,Phòng tắm riêng trong phòng,Ban công,Nhìn ra thành phố,Hồ bơi có tầm nhìn,Hồ bơi trên sân thượng,Điều hòa không khí,TV màn hình phẳng,Hệ thống cách âm,Minibar,WiFi miễn phí",
                    "Đồ vệ sinh cá nhân miễn phí,Nhà vệ sinh,Bồn tắm,Vòi sen,Khăn tắm,Dép,Toilet chung,Máy sấy tóc,Toilet phụ,Giấy vệ sinh",
                    "Nhìn ra thành phố,Nhìn ra biển","Các tầng trên đi lên bằng thang máy,Các tầng trên chỉ lên được bằng cầu thang,Ra trải giường,Tủ hoặc phòng để quần áo,Căn hộ riêng trong tòa nhà,Nước rửa tay, Minibar,Điều hòa không khí, Sàn lát gỗ,Không gây dị ứng,Sàn lát gạch/đá cẩm thạch,Hệ thống cách âm,Bàn ủi, Quạt máy,Sàn trải thảm,Thiết bị báo carbon monoxide,Tủ lạnh,Lò vi sóng,Ấm đun nước điện,Bếp nấu,Đồ bếp,Bàn ăn,Bếp,Bếp nhỏ,Ghế sofa,Bàn làm việc,Khu vực tiếp khách,Khu vực phòng ăn,TV,TV màn hình phẳng,Truyền hình cáp,Ban công,Ổ điện gần giường,Giá treo quần áo,Giường xếp,Giá phơi quần áo,Giường sofa,Máy điều hòa độc lập cho từng phòng",
                    true);
                Room room1_4 = new Room(1, "30 m²", 3_600_000.0, 3, 1_918_800.0, 1, 1, 8, 8, "Phòng 3 Người Nhìn Ra Thành Phố","Căn hộ nguyên căn,Bếp riêng,Phòng tắm riêng trong phòng,Ban công,Nhìn ra thành phố,Hồ bơi có tầm nhìn,Hồ bơi trên sân thượng,Điều hòa không khí,TV màn hình phẳng,Hệ thống cách âm,Minibar,WiFi miễn phí",
                    "Đồ vệ sinh cá nhân miễn phí,Nhà vệ sinh,Bồn tắm,Vòi sen,Khăn tắm,Dép,Toilet chung,Máy sấy tóc,Toilet phụ,Giấy vệ sinh",
                    "Nhìn ra thành phố,Nhìn ra biển","Các tầng trên đi lên bằng thang máy,Các tầng trên chỉ lên được bằng cầu thang,Ra trải giường,Tủ hoặc phòng để quần áo,Căn hộ riêng trong tòa nhà,Nước rửa tay, Minibar,Điều hòa không khí, Sàn lát gỗ,Không gây dị ứng,Sàn lát gạch/đá cẩm thạch,Hệ thống cách âm,Bàn ủi, Quạt máy,Sàn trải thảm,Thiết bị báo carbon monoxide,Tủ lạnh,Lò vi sóng,Ấm đun nước điện,Bếp nấu,Đồ bếp,Bàn ăn,Bếp,Bếp nhỏ,Ghế sofa,Bàn làm việc,Khu vực tiếp khách,Khu vực phòng ăn,TV,TV màn hình phẳng,Truyền hình cáp,Ban công,Ổ điện gần giường,Giá treo quần áo,Giường xếp,Giá phơi quần áo,Giường sofa,Máy điều hòa độc lập cho từng phòng",
                    true);
                Room room1_5 = new Room(1, "35 m²", 4_500_000.0, 4, 3_321_800.0, 0, 2, 7, 7, "Phòng Gia Đình Có Bồn Tắm","Căn hộ nguyên căn,Bếp riêng,Phòng tắm riêng trong phòng,Ban công,Nhìn ra thành phố,Hồ bơi có tầm nhìn,Hồ bơi trên sân thượng,Điều hòa không khí,TV màn hình phẳng,Hệ thống cách âm,Minibar,WiFi miễn phí",
                    "Đồ vệ sinh cá nhân miễn phí,Nhà vệ sinh,Bồn tắm,Vòi sen,Khăn tắm,Dép,Toilet chung,Máy sấy tóc,Toilet phụ,Giấy vệ sinh",
                    "Nhìn ra thành phố,Nhìn ra biển","Các tầng trên đi lên bằng thang máy,Các tầng trên chỉ lên được bằng cầu thang,Ra trải giường,Tủ hoặc phòng để quần áo,Căn hộ riêng trong tòa nhà,Nước rửa tay, Minibar,Điều hòa không khí, Sàn lát gỗ,Không gây dị ứng,Sàn lát gạch/đá cẩm thạch,Hệ thống cách âm,Bàn ủi, Quạt máy,Sàn trải thảm,Thiết bị báo carbon monoxide,Tủ lạnh,Lò vi sóng,Ấm đun nước điện,Bếp nấu,Đồ bếp,Bàn ăn,Bếp,Bếp nhỏ,Ghế sofa,Bàn làm việc,Khu vực tiếp khách,Khu vực phòng ăn,TV,TV màn hình phẳng,Truyền hình cáp,Ban công,Ổ điện gần giường,Giá treo quần áo,Giường xếp,Giá phơi quần áo,Giường sofa,Máy điều hòa độc lập cho từng phòng",
                    true);

                logger.info("insert data: " + roomRepository.save(room1_1));
                logger.info("insert data: " + roomRepository.save(room1_2));
                logger.info("insert data: " + roomRepository.save(room1_3));
                logger.info("insert data: " + roomRepository.save(room1_4));
                logger.info("insert data: " + roomRepository.save(room1_5));

                //Review
                Review re1_1 = new Review(1, "hung@gmail.com", "Khách sạn sạch đẹp, nhân viên nhiệt tình. Vị trí khá thuận tiện", 9);
                Review re1_2 = new Review(1, "truong@gmail.com", "sạch sẽ nhân viên thân thiện. nếu ra lại monhf vẫn đặt ks này ở đà nẵng", 9);
                Review re1_3 = new Review(1, "tran@gmail.com", "Gần biển, phòng rộng sạch sẽ. Nhân viên chu đáo , nhiệt tình. Có dịp sẽ quay lại ủng hộ khách sạn", 10);
                Review re1_4 = new Review(1, "gia@gmail.com", "tuy hạng phòng thấp nhất nhưng lại rộng và thoải mái", 10);
                Review re1_5 = new Review(1, "thanh@gmail.com", "Tất cả nhân viên đều tốt và tử tế. chỉ là tốt nhất. Cảm ơn tất cả !!!", 10);

                logger.info("insert data: " + reviewRepository.save(re1_1));
                logger.info("insert data: " + reviewRepository.save(re1_2));
                logger.info("insert data: " + reviewRepository.save(re1_3));
                logger.info("insert data: " + reviewRepository.save(re1_4));
                logger.info("insert data: " + reviewRepository.save(re1_5));

                //Image
                Image img1_0_0 = new Image(1, 0, "https://res.cloudinary.com/dei0obeww/image/upload/v1671632162/npbooking/Hai%20Trieu%20Hotel/382886073_thxu8x.jpg");
                Image img1_0_1 = new Image(1, 0, "https://res.cloudinary.com/dei0obeww/image/upload/v1671632162/npbooking/Hai%20Trieu%20Hotel/354446998_li3vax.jpg");
                Image img1_0_2 = new Image(1, 0, "https://res.cloudinary.com/dei0obeww/image/upload/v1671632161/npbooking/Hai%20Trieu%20Hotel/376290427_jdbavp.jpg");
                Image img1_0_3 = new Image(1, 0, "https://res.cloudinary.com/dei0obeww/image/upload/v1671632161/npbooking/Hai%20Trieu%20Hotel/376380161_vpe4pp.jpg");
                Image img1_0_4 = new Image(1, 0, "https://res.cloudinary.com/dei0obeww/image/upload/v1671632161/npbooking/Hai%20Trieu%20Hotel/254138741_uwbyrp.jpg");
                Image img1_0_5 = new Image(1, 0, "https://res.cloudinary.com/dei0obeww/image/upload/v1671632161/npbooking/Hai%20Trieu%20Hotel/376241112_lkdahi.jpg");
                Image img1_1_0 = new Image(1, 1, "https://res.cloudinary.com/dei0obeww/image/upload/v1671940476/npbooking/Hai%20Trieu%20Hotel/11_x7egxo.jpg");
                Image img1_1_1 = new Image(1, 1, "https://res.cloudinary.com/dei0obeww/image/upload/v1671940476/npbooking/Hai%20Trieu%20Hotel/12_ohbwzy.jpg");
                Image img1_1_2 = new Image(1, 1, "https://res.cloudinary.com/dei0obeww/image/upload/v1671940476/npbooking/Hai%20Trieu%20Hotel/13_r8g3pp.jpg");
                Image img1_1_3 = new Image(1, 1, "https://res.cloudinary.com/dei0obeww/image/upload/v1671940476/npbooking/Hai%20Trieu%20Hotel/14_wmtnzp.jpg");
                Image img1_1_4 = new Image(1, 1, "https://res.cloudinary.com/dei0obeww/image/upload/v1671940476/npbooking/Hai%20Trieu%20Hotel/15_bjpchj.jpg");
                Image img1_1_5 = new Image(1, 1, "https://res.cloudinary.com/dei0obeww/image/upload/v1671940476/npbooking/Hai%20Trieu%20Hotel/16_voyhug.jpg");
                Image img1_2_0 = new Image(1, 2, "https://res.cloudinary.com/dei0obeww/image/upload/v1671940476/npbooking/Hai%20Trieu%20Hotel/20_f7thkw.jpg");
                Image img1_2_1 = new Image(1, 2, "https://res.cloudinary.com/dei0obeww/image/upload/v1671940476/npbooking/Hai%20Trieu%20Hotel/21_qmurkb.jpg");
                Image img1_2_2 = new Image(1, 2, "https://res.cloudinary.com/dei0obeww/image/upload/v1671940476/npbooking/Hai%20Trieu%20Hotel/22_yibdbd.jpg");
                Image img1_2_3 = new Image(1, 2, "https://res.cloudinary.com/dei0obeww/image/upload/v1671940476/npbooking/Hai%20Trieu%20Hotel/23_vpschf.jpg");
                Image img1_2_4 = new Image(1, 2, "https://res.cloudinary.com/dei0obeww/image/upload/v1671940477/npbooking/Hai%20Trieu%20Hotel/24_jqpemw.jpg");
                Image img1_2_5 = new Image(1, 2, "https://res.cloudinary.com/dei0obeww/image/upload/v1671940477/npbooking/Hai%20Trieu%20Hotel/25_kj33e1.jpg");
                Image img1_3_0 = new Image(1, 3, "https://res.cloudinary.com/dei0obeww/image/upload/v1671940477/npbooking/Hai%20Trieu%20Hotel/30_wyzxuq.jpg");
                Image img1_3_1 = new Image(1, 3, "https://res.cloudinary.com/dei0obeww/image/upload/v1671940477/npbooking/Hai%20Trieu%20Hotel/31_vhsyg0.jpg");
                Image img1_3_2 = new Image(1, 3, "https://res.cloudinary.com/dei0obeww/image/upload/v1671940477/npbooking/Hai%20Trieu%20Hotel/32_xjmybs.jpg");
                Image img1_3_3 = new Image(1, 3, "https://res.cloudinary.com/dei0obeww/image/upload/v1671940477/npbooking/Hai%20Trieu%20Hotel/33_pjijhm.jpg");
                Image img1_3_4 = new Image(1, 3, "https://res.cloudinary.com/dei0obeww/image/upload/v1671940477/npbooking/Hai%20Trieu%20Hotel/34_mmpnpy.jpg");
                Image img1_3_5 = new Image(1, 3, "https://res.cloudinary.com/dei0obeww/image/upload/v1671940478/npbooking/Hai%20Trieu%20Hotel/35_jr6777.jpg");
                Image img1_4_0 = new Image(1, 4, "https://res.cloudinary.com/dei0obeww/image/upload/v1671940478/npbooking/Hai%20Trieu%20Hotel/40_qxt44h.jpg");
                Image img1_4_1 = new Image(1, 4, "https://res.cloudinary.com/dei0obeww/image/upload/v1671940478/npbooking/Hai%20Trieu%20Hotel/41_nu7ujk.jpg");
                Image img1_4_2 = new Image(1, 4, "https://res.cloudinary.com/dei0obeww/image/upload/v1671940478/npbooking/Hai%20Trieu%20Hotel/42_zaktjd.jpg");
                Image img1_4_3 = new Image(1, 4, "https://res.cloudinary.com/dei0obeww/image/upload/v1671940478/npbooking/Hai%20Trieu%20Hotel/43_tw7yim.jpg");
                Image img1_4_4 = new Image(1, 4, "https://res.cloudinary.com/dei0obeww/image/upload/v1671940478/npbooking/Hai%20Trieu%20Hotel/44_p3a8vu.jpg");
                Image img1_4_5 = new Image(1, 4, "https://res.cloudinary.com/dei0obeww/image/upload/v1671940478/npbooking/Hai%20Trieu%20Hotel/45_xbf7pv.jpg");
                Image img1_5_0 = new Image(1, 5, "https://res.cloudinary.com/dei0obeww/image/upload/v1671940478/npbooking/Hai%20Trieu%20Hotel/50_w3qr8n.jpg");
                Image img1_5_1 = new Image(1, 5, "https://res.cloudinary.com/dei0obeww/image/upload/v1671940478/npbooking/Hai%20Trieu%20Hotel/51_svneba.jpg");
                Image img1_5_2 = new Image(1, 5, "https://res.cloudinary.com/dei0obeww/image/upload/v1671940478/npbooking/Hai%20Trieu%20Hotel/52_i2npth.jpg");
                Image img1_5_3 = new Image(1, 5, "https://res.cloudinary.com/dei0obeww/image/upload/v1671940478/npbooking/Hai%20Trieu%20Hotel/51_svneba.jpg");
                Image img1_5_4 = new Image(1, 5, "https://res.cloudinary.com/dei0obeww/image/upload/v1671940478/npbooking/Hai%20Trieu%20Hotel/54_hokxpt.jpg");
                Image img1_5_5 = new Image(1, 5, "https://res.cloudinary.com/dei0obeww/image/upload/v1671940478/npbooking/Hai%20Trieu%20Hotel/55_adhbjt.jpg");

                logger.info("insert data: " + imageRepository.save(img1_0_0));
                logger.info("insert data: " + imageRepository.save(img1_0_1));
                logger.info("insert data: " + imageRepository.save(img1_0_2));
                logger.info("insert data: " + imageRepository.save(img1_0_3));
                logger.info("insert data: " + imageRepository.save(img1_0_4));
                logger.info("insert data: " + imageRepository.save(img1_0_5));
                logger.info("insert data: " + imageRepository.save(img1_1_0));
                logger.info("insert data: " + imageRepository.save(img1_1_1));
                logger.info("insert data: " + imageRepository.save(img1_1_2));
                logger.info("insert data: " + imageRepository.save(img1_1_3));
                logger.info("insert data: " + imageRepository.save(img1_1_4));
                logger.info("insert data: " + imageRepository.save(img1_1_5));
                logger.info("insert data: " + imageRepository.save(img1_2_0));
                logger.info("insert data: " + imageRepository.save(img1_2_1));
                logger.info("insert data: " + imageRepository.save(img1_2_2));
                logger.info("insert data: " + imageRepository.save(img1_2_3));
                logger.info("insert data: " + imageRepository.save(img1_2_4));
                logger.info("insert data: " + imageRepository.save(img1_2_5));
                logger.info("insert data: " + imageRepository.save(img1_3_0));
                logger.info("insert data: " + imageRepository.save(img1_3_1));
                logger.info("insert data: " + imageRepository.save(img1_3_2));
                logger.info("insert data: " + imageRepository.save(img1_3_3));
                logger.info("insert data: " + imageRepository.save(img1_3_4));
                logger.info("insert data: " + imageRepository.save(img1_3_5));
                logger.info("insert data: " + imageRepository.save(img1_4_0));
                logger.info("insert data: " + imageRepository.save(img1_4_1));
                logger.info("insert data: " + imageRepository.save(img1_4_2));
                logger.info("insert data: " + imageRepository.save(img1_4_3));
                logger.info("insert data: " + imageRepository.save(img1_4_4));
                logger.info("insert data: " + imageRepository.save(img1_4_5));
                logger.info("insert data: " + imageRepository.save(img1_5_0));
                logger.info("insert data: " + imageRepository.save(img1_5_1));
                logger.info("insert data: " + imageRepository.save(img1_5_2));
                logger.info("insert data: " + imageRepository.save(img1_5_3));
                logger.info("insert data: " + imageRepository.save(img1_5_4));
                logger.info("insert data: " + imageRepository.save(img1_5_5));


                //Hotel2
                Hotel ho2 = new Hotel("Raon Apartment and Hotel",
                        """
                                Raon Apartment and Hotel nằm bên bờ biển ở thành phố Đà Nẵng, cách Bãi biển Mỹ Khê 700 m và Cầu Sông Hàn 1,9 km. Trong số các tiện nghi của chỗ nghỉ này có dịch vụ tiền sảnh, bàn đặt tour và WiFi miễn phí trong toàn bộ khuôn viên. Nơi đây cung cấp dịch vụ lễ tân 24 giờ, dịch vụ phòng và dịch vụ thu đổi ngoại tệ cho khách.

                                Tất cả phòng nghỉ tại đây có máy điều hòa, TV truyền hình vệ tinh màn hình phẳng, minibar, ấm đun nước, vòi sen, dép và bàn làm việc. Một số phòng được bố trí khu vực bếp ăn nhỏ với lò vi sóng và tủ lạnh. Mỗi phòng đều được trang bị phòng tắm riêng, máy sấy tóc và ga trải giường.

                                Khu vực này nổi tiếng với hoạt động đi bộ đường dài và du khách cũng có thể thuê xe hơi tại khách sạn.

                                Raon Apartment and Hotel nằm trong bán kính 2,4 km từ Cầu khóa Tình yêu và 2,7 km từ Trung tâm thương mại Indochina Riverside. Sân bay gần nhất là sân bay quốc tế Đà Nẵng, cách đó 7 km, và chỗ nghỉ cung cấp dịch vụ đưa đón sân bay với một khoản phụ phí.""",
                        2,
                        "97A Hoàng Bích Sơn, Đà Nẵng, Việt Nam",
                        "Bãi đậu xe an toàn," +
                                "Ban công," +
                                "Điều hòa không khí," +
                                "Phòng tắm riêng," +
                                "Tầm nhìn ra khung cảnh," +
                                "Bếp nhỏ," +
                                "Wi-Fi miễn phí," +
                                "Vòi sen," +
                                "Minibar," +
                                "TV màn hình phẳng",
                        3,
                        false,
                        2.4,
                        true,
                        true,
                        "long@gmail.com");

                logger.info("insert data: " + hotelRepository.save(ho2));

                Room room2_1 = new Room(2, "22 m²", 600_000.0, 2, 200_000.0, 2, 0, 5, 5, "Phòng Superior 2 Giường Đơn","Căn hộ nguyên căn,Bếp riêng,Phòng tắm riêng trong phòng,Ban công,Nhìn ra thành phố,Hồ bơi có tầm nhìn,Hồ bơi trên sân thượng,Điều hòa không khí,TV màn hình phẳng,Hệ thống cách âm,Minibar,WiFi miễn phí",
                    "Đồ vệ sinh cá nhân miễn phí,Nhà vệ sinh,Bồn tắm,Vòi sen,Khăn tắm,Dép,Toilet chung,Máy sấy tóc,Toilet phụ,Giấy vệ sinh",
                    "Nhìn ra thành phố,Nhìn ra biển","Các tầng trên đi lên bằng thang máy,Các tầng trên chỉ lên được bằng cầu thang,Ra trải giường,Tủ hoặc phòng để quần áo,Căn hộ riêng trong tòa nhà,Nước rửa tay, Minibar,Điều hòa không khí, Sàn lát gỗ,Không gây dị ứng,Sàn lát gạch/đá cẩm thạch,Hệ thống cách âm,Bàn ủi, Quạt máy,Sàn trải thảm,Thiết bị báo carbon monoxide,Tủ lạnh,Lò vi sóng,Ấm đun nước điện,Bếp nấu,Đồ bếp,Bàn ăn,Bếp,Bếp nhỏ,Ghế sofa,Bàn làm việc,Khu vực tiếp khách,Khu vực phòng ăn,TV,TV màn hình phẳng,Truyền hình cáp,Ban công,Ổ điện gần giường,Giá treo quần áo,Giường xếp,Giá phơi quần áo,Giường sofa,Máy điều hòa độc lập cho từng phòng",
                    true);
                Room room2_2 = new Room(2, "26 m²", 700_000.0, 3, 250_000.0, 0, 2, 5, 5, "Phòng Superior","Căn hộ nguyên căn,Bếp riêng,Phòng tắm riêng trong phòng,Ban công,Nhìn ra thành phố,Hồ bơi có tầm nhìn,Hồ bơi trên sân thượng,Điều hòa không khí,TV màn hình phẳng,Hệ thống cách âm,Minibar,WiFi miễn phí",
                    "Đồ vệ sinh cá nhân miễn phí,Nhà vệ sinh,Bồn tắm,Vòi sen,Khăn tắm,Dép,Toilet chung,Máy sấy tóc,Toilet phụ,Giấy vệ sinh",
                    "Nhìn ra thành phố,Nhìn ra biển","Các tầng trên đi lên bằng thang máy,Các tầng trên chỉ lên được bằng cầu thang,Ra trải giường,Tủ hoặc phòng để quần áo,Căn hộ riêng trong tòa nhà,Nước rửa tay, Minibar,Điều hòa không khí, Sàn lát gỗ,Không gây dị ứng,Sàn lát gạch/đá cẩm thạch,Hệ thống cách âm,Bàn ủi, Quạt máy,Sàn trải thảm,Thiết bị báo carbon monoxide,Tủ lạnh,Lò vi sóng,Ấm đun nước điện,Bếp nấu,Đồ bếp,Bàn ăn,Bếp,Bếp nhỏ,Ghế sofa,Bàn làm việc,Khu vực tiếp khách,Khu vực phòng ăn,TV,TV màn hình phẳng,Truyền hình cáp,Ban công,Ổ điện gần giường,Giá treo quần áo,Giường xếp,Giá phơi quần áo,Giường sofa,Máy điều hòa độc lập cho từng phòng",
                    true);
                Room room2_3 = new Room(2, "30 m²", 825_000.0, 2, 552_800.0, 2, 0, 7, 7, "Studio Superior","Căn hộ nguyên căn,Bếp riêng,Phòng tắm riêng trong phòng,Ban công,Nhìn ra thành phố,Hồ bơi có tầm nhìn,Hồ bơi trên sân thượng,Điều hòa không khí,TV màn hình phẳng,Hệ thống cách âm,Minibar,WiFi miễn phí",
                    "Đồ vệ sinh cá nhân miễn phí,Nhà vệ sinh,Bồn tắm,Vòi sen,Khăn tắm,Dép,Toilet chung,Máy sấy tóc,Toilet phụ,Giấy vệ sinh",
                    "Nhìn ra thành phố,Nhìn ra biển","Các tầng trên đi lên bằng thang máy,Các tầng trên chỉ lên được bằng cầu thang,Ra trải giường,Tủ hoặc phòng để quần áo,Căn hộ riêng trong tòa nhà,Nước rửa tay, Minibar,Điều hòa không khí, Sàn lát gỗ,Không gây dị ứng,Sàn lát gạch/đá cẩm thạch,Hệ thống cách âm,Bàn ủi, Quạt máy,Sàn trải thảm,Thiết bị báo carbon monoxide,Tủ lạnh,Lò vi sóng,Ấm đun nước điện,Bếp nấu,Đồ bếp,Bàn ăn,Bếp,Bếp nhỏ,Ghế sofa,Bàn làm việc,Khu vực tiếp khách,Khu vực phòng ăn,TV,TV màn hình phẳng,Truyền hình cáp,Ban công,Ổ điện gần giường,Giá treo quần áo,Giường xếp,Giá phơi quần áo,Giường sofa,Máy điều hòa độc lập cho từng phòng",
                    true);
                Room room2_4 = new Room(2, "35 m²", 1_031_000.0, 3, 690_800.0, 0, 2, 8, 8, "Căn Hộ Studio","Căn hộ nguyên căn,Bếp riêng,Phòng tắm riêng trong phòng,Ban công,Nhìn ra thành phố,Hồ bơi có tầm nhìn,Hồ bơi trên sân thượng,Điều hòa không khí,TV màn hình phẳng,Hệ thống cách âm,Minibar,WiFi miễn phí",
                    "Đồ vệ sinh cá nhân miễn phí,Nhà vệ sinh,Bồn tắm,Vòi sen,Khăn tắm,Dép,Toilet chung,Máy sấy tóc,Toilet phụ,Giấy vệ sinh",
                    "Nhìn ra thành phố,Nhìn ra biển","Các tầng trên đi lên bằng thang máy,Các tầng trên chỉ lên được bằng cầu thang,Ra trải giường,Tủ hoặc phòng để quần áo,Căn hộ riêng trong tòa nhà,Nước rửa tay, Minibar,Điều hòa không khí, Sàn lát gỗ,Không gây dị ứng,Sàn lát gạch/đá cẩm thạch,Hệ thống cách âm,Bàn ủi, Quạt máy,Sàn trải thảm,Thiết bị báo carbon monoxide,Tủ lạnh,Lò vi sóng,Ấm đun nước điện,Bếp nấu,Đồ bếp,Bàn ăn,Bếp,Bếp nhỏ,Ghế sofa,Bàn làm việc,Khu vực tiếp khách,Khu vực phòng ăn,TV,TV màn hình phẳng,Truyền hình cáp,Ban công,Ổ điện gần giường,Giá treo quần áo,Giường xếp,Giá phơi quần áo,Giường sofa,Máy điều hòa độc lập cho từng phòng",
                    true);
                Room room2_5 = new Room(2, "42 m²", 1_820_000.0, 6, 1_219_800.0, 0, 3, 7, 7, "Suite Gia Đình Deluxe","Căn hộ nguyên căn,Bếp riêng,Phòng tắm riêng trong phòng,Ban công,Nhìn ra thành phố,Hồ bơi có tầm nhìn,Hồ bơi trên sân thượng,Điều hòa không khí,TV màn hình phẳng,Hệ thống cách âm,Minibar,WiFi miễn phí",
                    "Đồ vệ sinh cá nhân miễn phí,Nhà vệ sinh,Bồn tắm,Vòi sen,Khăn tắm,Dép,Toilet chung,Máy sấy tóc,Toilet phụ,Giấy vệ sinh",
                    "Nhìn ra thành phố,Nhìn ra biển","Các tầng trên đi lên bằng thang máy,Các tầng trên chỉ lên được bằng cầu thang,Ra trải giường,Tủ hoặc phòng để quần áo,Căn hộ riêng trong tòa nhà,Nước rửa tay, Minibar,Điều hòa không khí, Sàn lát gỗ,Không gây dị ứng,Sàn lát gạch/đá cẩm thạch,Hệ thống cách âm,Bàn ủi, Quạt máy,Sàn trải thảm,Thiết bị báo carbon monoxide,Tủ lạnh,Lò vi sóng,Ấm đun nước điện,Bếp nấu,Đồ bếp,Bàn ăn,Bếp,Bếp nhỏ,Ghế sofa,Bàn làm việc,Khu vực tiếp khách,Khu vực phòng ăn,TV,TV màn hình phẳng,Truyền hình cáp,Ban công,Ổ điện gần giường,Giá treo quần áo,Giường xếp,Giá phơi quần áo,Giường sofa,Máy điều hòa độc lập cho từng phòng",
                    true);

                logger.info("insert data: " + roomRepository.save(room2_1));
                logger.info("insert data: " + roomRepository.save(room2_2));
                logger.info("insert data: " + roomRepository.save(room2_3));
                logger.info("insert data: " + roomRepository.save(room2_4));
                logger.info("insert data: " + roomRepository.save(room2_5));

                Review re2_1 = new Review(2, "hung@gmail.com", "Ấn tượng từ nhân viên đến phòng studio rộng, thoáng", 7);
                Review re2_2 = new Review(2, "truong@gmail.com", "Nhân viên nhiệt tình niềm.nỡ check in check out nhanh chóng thuận tiện", 8);
                Review re2_3 = new Review(2, "tran@gmail.com", "Khách sạn gần biển, mát mẻ. nhân viên hỗ trợ nhiệt tình, chu đáo. Giá phòng hợp lý. Sẽ quay lại nếu có dịp", 7);
                Review re2_4 = new Review(2, "gia@gmail.com", "Khách sạn rộng rãi, phòng rộng và thoáng, nhân viên cực kì nice. Đi bộ ra biển hay đi bán đảo Sơn Trà, chợ Cồn, cầu Rồng, Hội An đều tiện lợi.", 7);
                Review re2_5 = new Review(2, "thanh@gmail.com", "Cực kì ấn tượng bởi sự tận tinh của khách sạn, ra biển cũng gần mà đi xemáy vào trung tâm cũng chỉ 5 phút là tha hô ăn sập chợ Cồn rui.", 7);

                logger.info("insert data: " + reviewRepository.save(re2_1));
                logger.info("insert data: " + reviewRepository.save(re2_2));
                logger.info("insert data: " + reviewRepository.save(re2_3));
                logger.info("insert data: " + reviewRepository.save(re2_4));
                logger.info("insert data: " + reviewRepository.save(re2_5));

                Image img2_0_0 = new Image(2, 0, "https://res.cloudinary.com/dei0obeww/image/upload/v1671813149/npbooking/Raon%20Apartment%20and%20Hotel/210973040_ga9nak.jpg");
                Image img2_0_1 = new Image(2, 0, "https://res.cloudinary.com/dei0obeww/image/upload/v1671813150/npbooking/Raon%20Apartment%20and%20Hotel/143063555_dqmwmn.jpg");
                Image img2_0_2 = new Image(2, 0, "https://res.cloudinary.com/dei0obeww/image/upload/v1671813149/npbooking/Raon%20Apartment%20and%20Hotel/224815227_a5v9rg.jpg");
                Image img2_0_3 = new Image(2, 0, "https://res.cloudinary.com/dei0obeww/image/upload/v1671813149/npbooking/Raon%20Apartment%20and%20Hotel/254138767_exgfh8.jpg");
                Image img2_0_4 = new Image(2, 0, "https://res.cloudinary.com/dei0obeww/image/upload/v1671813149/npbooking/Raon%20Apartment%20and%20Hotel/142934612_lfc3ve.jpg");
                Image img2_0_5 = new Image(2, 0, "https://res.cloudinary.com/dei0obeww/image/upload/v1671813545/npbooking/Raon%20Apartment%20and%20Hotel/258258443_hwkffr.jpg");
                Image img2_1_0 = new Image(2, 1, "https://res.cloudinary.com/dei0obeww/image/upload/v1671941494/npbooking/Raon%20Apartment%20and%20Hotel/10_xde5mk.jpg");
                Image img2_1_1 = new Image(2, 1, "https://res.cloudinary.com/dei0obeww/image/upload/v1671941494/npbooking/Raon%20Apartment%20and%20Hotel/11_oiiduh.jpg");
                Image img2_1_2 = new Image(2, 1, "https://res.cloudinary.com/dei0obeww/image/upload/v1671941494/npbooking/Raon%20Apartment%20and%20Hotel/12_ok6nkf.jpg");
                Image img2_1_3 = new Image(2, 1, "https://res.cloudinary.com/dei0obeww/image/upload/v1671941494/npbooking/Raon%20Apartment%20and%20Hotel/13_sorhmb.jpg");
                Image img2_1_4 = new Image(2, 1, "https://res.cloudinary.com/dei0obeww/image/upload/v1671941494/npbooking/Raon%20Apartment%20and%20Hotel/14_giwtqc.jpg");
                Image img2_1_5 = new Image(2, 1, "https://res.cloudinary.com/dei0obeww/image/upload/v1671941494/npbooking/Raon%20Apartment%20and%20Hotel/15_rbvh9o.jpg");
                Image img2_2_0 = new Image(2, 2, "https://res.cloudinary.com/dei0obeww/image/upload/v1671941494/npbooking/Raon%20Apartment%20and%20Hotel/20_ssuscs.jpg");
                Image img2_2_1 = new Image(2, 2, "https://res.cloudinary.com/dei0obeww/image/upload/v1671941494/npbooking/Raon%20Apartment%20and%20Hotel/21_syfo1c.jpg");
                Image img2_2_2 = new Image(2, 2, "https://res.cloudinary.com/dei0obeww/image/upload/v1671941494/npbooking/Raon%20Apartment%20and%20Hotel/22_oxkfo5.jpg");
                Image img2_2_3 = new Image(2, 2, "https://res.cloudinary.com/dei0obeww/image/upload/v1671941494/npbooking/Raon%20Apartment%20and%20Hotel/23_wgqlim.jpg");
                Image img2_2_4 = new Image(2, 2, "https://res.cloudinary.com/dei0obeww/image/upload/v1671941494/npbooking/Raon%20Apartment%20and%20Hotel/24_rxgnhv.jpg");
                Image img2_2_5 = new Image(2, 2, "https://res.cloudinary.com/dei0obeww/image/upload/v1671941495/npbooking/Raon%20Apartment%20and%20Hotel/25_sahl54.jpg");
                Image img2_3_0 = new Image(2, 3, "https://res.cloudinary.com/dei0obeww/image/upload/v1671941495/npbooking/Raon%20Apartment%20and%20Hotel/30_mzsaxz.jpg");
                Image img2_3_1 = new Image(2, 3, "https://res.cloudinary.com/dei0obeww/image/upload/v1671941495/npbooking/Raon%20Apartment%20and%20Hotel/31_yjzjyh.jpg");
                Image img2_3_2 = new Image(2, 3, "https://res.cloudinary.com/dei0obeww/image/upload/v1671941495/npbooking/Raon%20Apartment%20and%20Hotel/32_ceq6ix.jpg");
                Image img2_3_3 = new Image(2, 3, "https://res.cloudinary.com/dei0obeww/image/upload/v1671941495/npbooking/Raon%20Apartment%20and%20Hotel/33_wapghk.jpg");
                Image img2_3_4 = new Image(2, 3, "https://res.cloudinary.com/dei0obeww/image/upload/v1671941495/npbooking/Raon%20Apartment%20and%20Hotel/34_tr6pc9.jpg");
                Image img2_3_5 = new Image(2, 3, "https://res.cloudinary.com/dei0obeww/image/upload/v1671941495/npbooking/Raon%20Apartment%20and%20Hotel/35_i5i1wf.jpg");
                Image img2_4_0 = new Image(2, 4, "https://res.cloudinary.com/dei0obeww/image/upload/v1671941496/npbooking/Raon%20Apartment%20and%20Hotel/40_gpoeam.jpg");
                Image img2_4_1 = new Image(2, 4, "https://res.cloudinary.com/dei0obeww/image/upload/v1671941495/npbooking/Raon%20Apartment%20and%20Hotel/41_obbh3o.jpg");
                Image img2_4_2 = new Image(2, 4, "https://res.cloudinary.com/dei0obeww/image/upload/v1671941496/npbooking/Raon%20Apartment%20and%20Hotel/42_fb43au.jpg");
                Image img2_4_3 = new Image(2, 4, "https://res.cloudinary.com/dei0obeww/image/upload/v1671941496/npbooking/Raon%20Apartment%20and%20Hotel/43_dxcvtl.jpg");
                Image img2_4_4 = new Image(2, 4, "https://res.cloudinary.com/dei0obeww/image/upload/v1671941496/npbooking/Raon%20Apartment%20and%20Hotel/44_u8ztn3.jpg");
                Image img2_4_5 = new Image(2, 4, "https://res.cloudinary.com/dei0obeww/image/upload/v1671941496/npbooking/Raon%20Apartment%20and%20Hotel/45_shmi28.jpg");
                Image img2_5_0 = new Image(2, 5, "https://res.cloudinary.com/dei0obeww/image/upload/v1671941496/npbooking/Raon%20Apartment%20and%20Hotel/50_ucbehf.jpg");
                Image img2_5_1 = new Image(2, 5, "https://res.cloudinary.com/dei0obeww/image/upload/v1671941496/npbooking/Raon%20Apartment%20and%20Hotel/51_mlwsur.jpg");
                Image img2_5_2 = new Image(2, 5, "https://res.cloudinary.com/dei0obeww/image/upload/v1671941496/npbooking/Raon%20Apartment%20and%20Hotel/52_t4dsy3.jpg");
                Image img2_5_3 = new Image(2, 5, "https://res.cloudinary.com/dei0obeww/image/upload/v1671941496/npbooking/Raon%20Apartment%20and%20Hotel/53_p3yjuz.jpg");
                Image img2_5_4 = new Image(2, 5, "https://res.cloudinary.com/dei0obeww/image/upload/v1671941496/npbooking/Raon%20Apartment%20and%20Hotel/54_s4c2ah.jpg");
                Image img2_5_5 = new Image(2, 5, "https://res.cloudinary.com/dei0obeww/image/upload/v1671941497/npbooking/Raon%20Apartment%20and%20Hotel/55_ny3cc8.jpg");

                logger.info("insert data: " + imageRepository.save(img2_0_0));
                logger.info("insert data: " + imageRepository.save(img2_0_1));
                logger.info("insert data: " + imageRepository.save(img2_0_2));
                logger.info("insert data: " + imageRepository.save(img2_0_3));
                logger.info("insert data: " + imageRepository.save(img2_0_4));
                logger.info("insert data: " + imageRepository.save(img2_0_5));
                logger.info("insert data: " + imageRepository.save(img2_1_0));
                logger.info("insert data: " + imageRepository.save(img2_1_1));
                logger.info("insert data: " + imageRepository.save(img2_1_2));
                logger.info("insert data: " + imageRepository.save(img2_1_3));
                logger.info("insert data: " + imageRepository.save(img2_1_4));
                logger.info("insert data: " + imageRepository.save(img2_1_5));
                logger.info("insert data: " + imageRepository.save(img2_2_0));
                logger.info("insert data: " + imageRepository.save(img2_2_1));
                logger.info("insert data: " + imageRepository.save(img2_2_2));
                logger.info("insert data: " + imageRepository.save(img2_2_3));
                logger.info("insert data: " + imageRepository.save(img2_2_4));
                logger.info("insert data: " + imageRepository.save(img2_2_5));
                logger.info("insert data: " + imageRepository.save(img2_3_0));
                logger.info("insert data: " + imageRepository.save(img2_3_1));
                logger.info("insert data: " + imageRepository.save(img2_3_2));
                logger.info("insert data: " + imageRepository.save(img2_3_3));
                logger.info("insert data: " + imageRepository.save(img2_3_4));
                logger.info("insert data: " + imageRepository.save(img2_3_5));
                logger.info("insert data: " + imageRepository.save(img2_4_0));
                logger.info("insert data: " + imageRepository.save(img2_4_1));
                logger.info("insert data: " + imageRepository.save(img2_4_2));
                logger.info("insert data: " + imageRepository.save(img2_4_3));
                logger.info("insert data: " + imageRepository.save(img2_4_4));
                logger.info("insert data: " + imageRepository.save(img2_4_5));
                logger.info("insert data: " + imageRepository.save(img2_5_0));
                logger.info("insert data: " + imageRepository.save(img2_5_1));
                logger.info("insert data: " + imageRepository.save(img2_5_2));
                logger.info("insert data: " + imageRepository.save(img2_5_3));
                logger.info("insert data: " + imageRepository.save(img2_5_4));
                logger.info("insert data: " + imageRepository.save(img2_5_5));

                //Hotel3
                Hotel ho3 = new Hotel("Alani Hotel & Spa",
                        """
                                Aria Hotel & Spa nằm ở thành phố Đà Nẵng, cách Bãi biển Mỹ Khê 3 phút đi bộ. Du khách có thể thư giãn trên sân hiên tắm nắng và thưởng ngoạn quang cảnh biển trong khi bơi ở hồ bơi trên sân thượng. Chỗ nghỉ cung cấp WiFi miễn phí trong toàn bộ khuôn viên.

                                Phòng nghỉ tại đây có tủ để quần áo, khu vực ghế ngồi, máy điều hòa, TV màn hình phẳng, minibar và két an toàn các nhân. Một số phòng cho tầm nhìn ra quang cảnh biển/thành phố. Mỗi phòng đều được bố trí phòng tắm riêng với tiện nghi vòi sen và máy sấy tóc. Khăn tắm và đồ vệ sinh cá nhân miễn phí được cung cấp nhằm tạo thuận tiện cho du khách.

                                Nhân viên tại quầy lễ tân 24 giờ có thể hỗ trợ du khách với các dịch vụ giữ hành lý, giặt là, thuê xe đạp và xe hơi cũng như sắp xếp tour du lịch. Trong khuôn viên chỗ nghỉ còn có trung tâm thể dục, phòng xông hơi khô, phòng xông hơi ướt và tiện nghi tổ chức tiệc/hội họp.

                                Bảo tàng Chăm nằm trong bán kính 2,6 km từ Aria Hotel & Spa trong khi Cầu Sông Hàn cách đó 2,9 km. Sân bay gần nhất là sân bay quốc tế Đà Nẵng, cách Aria Hotel & Spa 5 km. Dịch vụ đưa đón có thể được bố trí với một khoản phụ phí.

                                Nằm cách Bãi biển Mỹ Khê vài bước chân, Aria Hotel & Spa có hồ bơi ngoài trời, dịch vụ mát-xa, nhà hàng và sảnh khách - quầy bar nhìn ra Thái Bình Dương. WiFi được cung cấp miễn phí trong toàn bộ khách sạn.

                                Khách sạn cung cấp phòng nghỉ trang nhã với tầm nhìn ra quang cảnh biển và thành phố. Tất cả các phòng đều được trang bị ghế sofa thoải mái, TV màn hình LED với các kênh truyền hình cáp quốc tế, két an toàn và minibar. Một số phòng còn có bồn tắm và ban công riêng. Phòng tắm riêng đi kèm đồ vệ sinh cá nhân miễn phí, máy sấy tóc và dép đi trong phòng.

                                Du khách có thể dùng bữa tại nhà hàng trong khuôn viên với hải sản tươi ngon và các món ăn ngon của Việt Nam. Đồ ăn nhẹ và cocktail được phục vụ tại quán Pool Bar. Trong khuôn viên còn có các tiện nghi như phòng tập thể dục, phòng xông hơi khô và phòng xông hơi ướt.

                                Aria Hotel & Spa nằm cách sân bay quốc tế Đà Nẵng 10 phút lái xe. Chỗ nghỉ nằm trong bán kính 30 phút lái xe từ thành phố Hội An, 10 phút từ Chợ Hàn, siêu thị Lotte Mart, trung tâm thương mại Vincom Mall hoặc Bảo tàng Chăm.""",
                        2,
                        "134-136-138 Tran Bach Dang, Đà Nẵng, Việt Nam",
                        "1 hồ bơi, " +
                                "Xe đưa đón sân bay, " +
                                "Phòng không hút thuốc, " +
                                "Trung tâm thể dục, " +
                                "Chỗ đỗ xe miễn phi,́ " +
                                "Dịch vụ phòng, " +
                                "WiFi có ở mọi khu vực, " +
                                "Quầy bar, " +
                                "Bữa sáng tốt, " +
                                "Giáp biển,",
                        4,
                        true,
                        3.3,
                        true,
                        true,
                        "long@gmail.com");

                logger.info("insert data: " + hotelRepository.save(ho3));

                Room room3_1 = new Room(3, "55 m²", 3_312_000.0, 5, 1_357_920.0, 0, 2, 3, 3, "Suite Nhìn Ra Biển","Căn hộ nguyên căn,Bếp riêng,Phòng tắm riêng trong phòng,Ban công,Nhìn ra thành phố,Hồ bơi có tầm nhìn,Hồ bơi trên sân thượng,Điều hòa không khí,TV màn hình phẳng,Hệ thống cách âm,Minibar,WiFi miễn phí",
                    "Đồ vệ sinh cá nhân miễn phí,Nhà vệ sinh,Bồn tắm,Vòi sen,Khăn tắm,Dép,Toilet chung,Máy sấy tóc,Toilet phụ,Giấy vệ sinh",
                    "Nhìn ra thành phố,Nhìn ra biển","Các tầng trên đi lên bằng thang máy,Các tầng trên chỉ lên được bằng cầu thang,Ra trải giường,Tủ hoặc phòng để quần áo,Căn hộ riêng trong tòa nhà,Nước rửa tay, Minibar,Điều hòa không khí, Sàn lát gỗ,Không gây dị ứng,Sàn lát gạch/đá cẩm thạch,Hệ thống cách âm,Bàn ủi, Quạt máy,Sàn trải thảm,Thiết bị báo carbon monoxide,Tủ lạnh,Lò vi sóng,Ấm đun nước điện,Bếp nấu,Đồ bếp,Bàn ăn,Bếp,Bếp nhỏ,Ghế sofa,Bàn làm việc,Khu vực tiếp khách,Khu vực phòng ăn,TV,TV màn hình phẳng,Truyền hình cáp,Ban công,Ổ điện gần giường,Giá treo quần áo,Giường xếp,Giá phơi quần áo,Giường sofa,Máy điều hòa độc lập cho từng phòng",
                    true);
                Room room3_2 = new Room(3, "18 m²", 993_600.0, 2, 407_620.0, 0, 1, 5, 5, "Phòng Giường Đôi Hạng Tiết Kiệm","Căn hộ nguyên căn,Bếp riêng,Phòng tắm riêng trong phòng,Ban công,Nhìn ra thành phố,Hồ bơi có tầm nhìn,Hồ bơi trên sân thượng,Điều hòa không khí,TV màn hình phẳng,Hệ thống cách âm,Minibar,WiFi miễn phí",
                    "Đồ vệ sinh cá nhân miễn phí,Nhà vệ sinh,Bồn tắm,Vòi sen,Khăn tắm,Dép,Toilet chung,Máy sấy tóc,Toilet phụ,Giấy vệ sinh",
                    "Nhìn ra thành phố,Nhìn ra biển","Các tầng trên đi lên bằng thang máy,Các tầng trên chỉ lên được bằng cầu thang,Ra trải giường,Tủ hoặc phòng để quần áo,Căn hộ riêng trong tòa nhà,Nước rửa tay, Minibar,Điều hòa không khí, Sàn lát gỗ,Không gây dị ứng,Sàn lát gạch/đá cẩm thạch,Hệ thống cách âm,Bàn ủi, Quạt máy,Sàn trải thảm,Thiết bị báo carbon monoxide,Tủ lạnh,Lò vi sóng,Ấm đun nước điện,Bếp nấu,Đồ bếp,Bàn ăn,Bếp,Bếp nhỏ,Ghế sofa,Bàn làm việc,Khu vực tiếp khách,Khu vực phòng ăn,TV,TV màn hình phẳng,Truyền hình cáp,Ban công,Ổ điện gần giường,Giá treo quần áo,Giường xếp,Giá phơi quần áo,Giường sofa,Máy điều hòa độc lập cho từng phòng",
                    true);
                Room room3_3 = new Room(3, "18 m²", 1_166_000.0, 2, 478_800.0, 0, 1, 6, 6, "Phòng Có Cửa sổ Nhìn Ra Bờ Biển","Căn hộ nguyên căn,Bếp riêng,Phòng tắm riêng trong phòng,Ban công,Nhìn ra thành phố,Hồ bơi có tầm nhìn,Hồ bơi trên sân thượng,Điều hòa không khí,TV màn hình phẳng,Hệ thống cách âm,Minibar,WiFi miễn phí",
                    "Đồ vệ sinh cá nhân miễn phí,Nhà vệ sinh,Bồn tắm,Vòi sen,Khăn tắm,Dép,Toilet chung,Máy sấy tóc,Toilet phụ,Giấy vệ sinh",
                    "Nhìn ra thành phố,Nhìn ra biển","Các tầng trên đi lên bằng thang máy,Các tầng trên chỉ lên được bằng cầu thang,Ra trải giường,Tủ hoặc phòng để quần áo,Căn hộ riêng trong tòa nhà,Nước rửa tay, Minibar,Điều hòa không khí, Sàn lát gỗ,Không gây dị ứng,Sàn lát gạch/đá cẩm thạch,Hệ thống cách âm,Bàn ủi, Quạt máy,Sàn trải thảm,Thiết bị báo carbon monoxide,Tủ lạnh,Lò vi sóng,Ấm đun nước điện,Bếp nấu,Đồ bếp,Bàn ăn,Bếp,Bếp nhỏ,Ghế sofa,Bàn làm việc,Khu vực tiếp khách,Khu vực phòng ăn,TV,TV màn hình phẳng,Truyền hình cáp,Ban công,Ổ điện gần giường,Giá treo quần áo,Giường xếp,Giá phơi quần áo,Giường sofa,Máy điều hòa độc lập cho từng phòng",
                    true);
                Room room3_4 = new Room(3, "25 m²", 1_215_000.0, 2, 489_100.0, 2, 0, 4, 4, "Phòng 2 Giường Đơn Nhìn Ra Thành Phố","Căn hộ nguyên căn,Bếp riêng,Phòng tắm riêng trong phòng,Ban công,Nhìn ra thành phố,Hồ bơi có tầm nhìn,Hồ bơi trên sân thượng,Điều hòa không khí,TV màn hình phẳng,Hệ thống cách âm,Minibar,WiFi miễn phí",
                    "Đồ vệ sinh cá nhân miễn phí,Nhà vệ sinh,Bồn tắm,Vòi sen,Khăn tắm,Dép,Toilet chung,Máy sấy tóc,Toilet phụ,Giấy vệ sinh",
                    "Nhìn ra thành phố,Nhìn ra biển","Các tầng trên đi lên bằng thang máy,Các tầng trên chỉ lên được bằng cầu thang,Ra trải giường,Tủ hoặc phòng để quần áo,Căn hộ riêng trong tòa nhà,Nước rửa tay, Minibar,Điều hòa không khí, Sàn lát gỗ,Không gây dị ứng,Sàn lát gạch/đá cẩm thạch,Hệ thống cách âm,Bàn ủi, Quạt máy,Sàn trải thảm,Thiết bị báo carbon monoxide,Tủ lạnh,Lò vi sóng,Ấm đun nước điện,Bếp nấu,Đồ bếp,Bàn ăn,Bếp,Bếp nhỏ,Ghế sofa,Bàn làm việc,Khu vực tiếp khách,Khu vực phòng ăn,TV,TV màn hình phẳng,Truyền hình cáp,Ban công,Ổ điện gần giường,Giá treo quần áo,Giường xếp,Giá phơi quần áo,Giường sofa,Máy điều hòa độc lập cho từng phòng",
                    true);
                Room room3_5 = new Room(3, "30 m²", 1_980_000.0, 4, 811_800.0, 0, 2, 7, 7, "Phòng 4 Người Tiện nghi","Căn hộ nguyên căn,Bếp riêng,Phòng tắm riêng trong phòng,Ban công,Nhìn ra thành phố,Hồ bơi có tầm nhìn,Hồ bơi trên sân thượng,Điều hòa không khí,TV màn hình phẳng,Hệ thống cách âm,Minibar,WiFi miễn phí",
                    "Đồ vệ sinh cá nhân miễn phí,Nhà vệ sinh,Bồn tắm,Vòi sen,Khăn tắm,Dép,Toilet chung,Máy sấy tóc,Toilet phụ,Giấy vệ sinh",
                    "Nhìn ra thành phố,Nhìn ra biển","Các tầng trên đi lên bằng thang máy,Các tầng trên chỉ lên được bằng cầu thang,Ra trải giường,Tủ hoặc phòng để quần áo,Căn hộ riêng trong tòa nhà,Nước rửa tay, Minibar,Điều hòa không khí, Sàn lát gỗ,Không gây dị ứng,Sàn lát gạch/đá cẩm thạch,Hệ thống cách âm,Bàn ủi, Quạt máy,Sàn trải thảm,Thiết bị báo carbon monoxide,Tủ lạnh,Lò vi sóng,Ấm đun nước điện,Bếp nấu,Đồ bếp,Bàn ăn,Bếp,Bếp nhỏ,Ghế sofa,Bàn làm việc,Khu vực tiếp khách,Khu vực phòng ăn,TV,TV màn hình phẳng,Truyền hình cáp,Ban công,Ổ điện gần giường,Giá treo quần áo,Giường xếp,Giá phơi quần áo,Giường sofa,Máy điều hòa độc lập cho từng phòng",
                    true);

                logger.info("insert data: " + roomRepository.save(room3_1));
                logger.info("insert data: " + roomRepository.save(room3_2));
                logger.info("insert data: " + roomRepository.save(room3_3));
                logger.info("insert data: " + roomRepository.save(room3_4));
                logger.info("insert data: " + roomRepository.save(room3_5));

                Review re3_1 = new Review(3, "hung@gmail.com", "Viu biển đẹp. Ks sạch sẽ . Nhân viên thân thiện . Sẽ còn quay lại.", 10);
                Review re3_2 = new Review(3, "truong@gmail.com", "Nhân viên lễ Tân Thuý Hiền trát hoà nhã thân thiện đẳng lòng trợ giúp những gì chúng tôi cần", 8);
                Review re3_3 = new Review(3, "tran@gmail.com", "Khách sạn gần biển, mát mẻ. nhân viên hỗ trợ nhiệt tình, chu đáo. Giá phòng hợp lý. Sẽ quay lại nếu có dịp", 10);
                Review re3_4 = new Review(3, "gia@gmail.com", "Điểm tâm ngon , view hồ bơi đẹp , nhan viên thân thiện", 10);
                Review re3_5 = new Review(3, "thanh@gmail.com", "Vị trí khách sạn rất thuận tiện, xung quanh nhiều quán ăn và siêu thị, đi bộ 3 phút ra đến bãi biển. Mình và bạn trai đặt phòng view hướng biển, phòng ốc sạch sẽ thoáng mát, tiện nghi đầy đủ. Bữa ăn sáng phong phú đa dạng, bạn trai mình là người nước ngoài, khen phở ở khách sạn ngon hơn cả quán ăn bên ngoài. Nhân viên khách sạn rất nhiệt tình thân thiện. Nếu có dịp quay trở lại Đà Nẵng, nhất định sẽ lại book Aria", 10);

                logger.info("insert data: " + reviewRepository.save(re3_1));
                logger.info("insert data: " + reviewRepository.save(re3_2));
                logger.info("insert data: " + reviewRepository.save(re3_3));
                logger.info("insert data: " + reviewRepository.save(re3_4));
                logger.info("insert data: " + reviewRepository.save(re3_5));

                Image img3_0_0 = new Image(3, 0, "https://res.cloudinary.com/dei0obeww/image/upload/v1671871927/npbooking/Alani%20Hotel%20Spa/398485506_kbwy92.jpg");
                Image img3_0_1 = new Image(3, 0, "https://res.cloudinary.com/dei0obeww/image/upload/v1671871927/npbooking/Alani%20Hotel%20Spa/398289845_kxnu6n.jpg");
                Image img3_0_2 = new Image(3, 0, "https://res.cloudinary.com/dei0obeww/image/upload/v1671871927/npbooking/Alani%20Hotel%20Spa/394459904_eqkxzq.jpg");
                Image img3_0_3 = new Image(3, 0, "https://res.cloudinary.com/dei0obeww/image/upload/v1671871927/npbooking/Alani%20Hotel%20Spa/161988860_gocb1b.jpg");
                Image img3_0_4 = new Image(3, 0, "https://res.cloudinary.com/dei0obeww/image/upload/v1671871927/npbooking/Alani%20Hotel%20Spa/361741021_mffuxj.jpg");
                Image img3_0_5 = new Image(3, 0, "https://res.cloudinary.com/dei0obeww/image/upload/v1671871927/npbooking/Alani%20Hotel%20Spa/352845597_kexdrx.jpg");
                Image img3_1_0 = new Image(3, 1, "https://res.cloudinary.com/dei0obeww/image/upload/v1671942478/npbooking/Alani%20Hotel%20Spa/10_choaxh.jpg");
                Image img3_1_1 = new Image(3, 1, "https://res.cloudinary.com/dei0obeww/image/upload/v1671942478/npbooking/Alani%20Hotel%20Spa/11_kny00e.jpg");
                Image img3_1_2 = new Image(3, 1, "https://res.cloudinary.com/dei0obeww/image/upload/v1671942478/npbooking/Alani%20Hotel%20Spa/12_qum3bz.jpg");
                Image img3_1_3 = new Image(3, 1, "https://res.cloudinary.com/dei0obeww/image/upload/v1671942478/npbooking/Alani%20Hotel%20Spa/13_c54abc.jpg");
                Image img3_1_4 = new Image(3, 1, "https://res.cloudinary.com/dei0obeww/image/upload/v1671942478/npbooking/Alani%20Hotel%20Spa/14_uyvhj7.jpg");
                Image img3_1_5 = new Image(3, 1, "https://res.cloudinary.com/dei0obeww/image/upload/v1671942478/npbooking/Alani%20Hotel%20Spa/15_vbddg2.jpg");
                Image img3_2_0 = new Image(3, 2, "https://res.cloudinary.com/dei0obeww/image/upload/v1671942479/npbooking/Alani%20Hotel%20Spa/20_f5kp1q.jpg");
                Image img3_2_1 = new Image(3, 2, "https://res.cloudinary.com/dei0obeww/image/upload/v1671942479/npbooking/Alani%20Hotel%20Spa/21_fgywnb.jpg");
                Image img3_2_2 = new Image(3, 2, "https://res.cloudinary.com/dei0obeww/image/upload/v1671942479/npbooking/Alani%20Hotel%20Spa/22_sgq8dz.jpg");
                Image img3_2_3 = new Image(3, 2, "https://res.cloudinary.com/dei0obeww/image/upload/v1671942479/npbooking/Alani%20Hotel%20Spa/23_sv1yrj.jpg");
                Image img3_3_0 = new Image(3, 3, "https://res.cloudinary.com/dei0obeww/image/upload/v1671942479/npbooking/Alani%20Hotel%20Spa/30_j4zoyi.jpg");
                Image img3_3_1 = new Image(3, 3, "https://res.cloudinary.com/dei0obeww/image/upload/v1671942479/npbooking/Alani%20Hotel%20Spa/31_ywazpe.jpg");
                Image img3_3_2 = new Image(3, 3, "https://res.cloudinary.com/dei0obeww/image/upload/v1671942479/npbooking/Alani%20Hotel%20Spa/32_mail4f.jpg");
                Image img3_3_3 = new Image(3, 3, "https://res.cloudinary.com/dei0obeww/image/upload/v1671942480/npbooking/Alani%20Hotel%20Spa/33_s7dihn.jpg");
                Image img3_3_4 = new Image(3, 3, "https://res.cloudinary.com/dei0obeww/image/upload/v1671942480/npbooking/Alani%20Hotel%20Spa/34_aw26zf.jpg");
                Image img3_4_0 = new Image(3, 4, "https://res.cloudinary.com/dei0obeww/image/upload/v1671942480/npbooking/Alani%20Hotel%20Spa/40_fixmhs.jpg");
                Image img3_4_1 = new Image(3, 4, "https://res.cloudinary.com/dei0obeww/image/upload/v1671942480/npbooking/Alani%20Hotel%20Spa/41_timuua.jpg");
                Image img3_4_2 = new Image(3, 4, "https://res.cloudinary.com/dei0obeww/image/upload/v1671942480/npbooking/Alani%20Hotel%20Spa/42_wanib3.jpg");
                Image img3_4_3 = new Image(3, 4, "https://res.cloudinary.com/dei0obeww/image/upload/v1671942480/npbooking/Alani%20Hotel%20Spa/43_uttpbj.jpg");
                Image img3_4_4 = new Image(3, 4, "https://res.cloudinary.com/dei0obeww/image/upload/v1671942480/npbooking/Alani%20Hotel%20Spa/44_fcpgwk.jpg");
                Image img3_5_0 = new Image(3, 5, "https://res.cloudinary.com/dei0obeww/image/upload/v1671942480/npbooking/Alani%20Hotel%20Spa/50_xv6xh0.jpg");
                Image img3_5_1 = new Image(3, 5, "https://res.cloudinary.com/dei0obeww/image/upload/v1671942480/npbooking/Alani%20Hotel%20Spa/51_uxg3pl.jpg");
                Image img3_5_2 = new Image(3, 5, "https://res.cloudinary.com/dei0obeww/image/upload/v1671942480/npbooking/Alani%20Hotel%20Spa/52_h42q0v.jpg");
                Image img3_5_3 = new Image(3, 5, "https://res.cloudinary.com/dei0obeww/image/upload/v1671942481/npbooking/Alani%20Hotel%20Spa/53_fcm1n2.jpg");
                Image img3_5_4 = new Image(3, 5, "https://res.cloudinary.com/dei0obeww/image/upload/v1671942481/npbooking/Alani%20Hotel%20Spa/54_bxfb4n.jpg");
                Image img3_5_5 = new Image(3, 5, "https://res.cloudinary.com/dei0obeww/image/upload/v1671942481/npbooking/Alani%20Hotel%20Spa/55_wz7vhs.jpg");

                logger.info("insert data: " + imageRepository.save(img3_0_0));
                logger.info("insert data: " + imageRepository.save(img3_0_1));
                logger.info("insert data: " + imageRepository.save(img3_0_2));
                logger.info("insert data: " + imageRepository.save(img3_0_3));
                logger.info("insert data: " + imageRepository.save(img3_0_4));
                logger.info("insert data: " + imageRepository.save(img3_0_5));
                logger.info("insert data: " + imageRepository.save(img3_1_0));
                logger.info("insert data: " + imageRepository.save(img3_1_1));
                logger.info("insert data: " + imageRepository.save(img3_1_2));
                logger.info("insert data: " + imageRepository.save(img3_1_3));
                logger.info("insert data: " + imageRepository.save(img3_1_4));
                logger.info("insert data: " + imageRepository.save(img3_1_5));
                logger.info("insert data: " + imageRepository.save(img3_2_0));
                logger.info("insert data: " + imageRepository.save(img3_2_1));
                logger.info("insert data: " + imageRepository.save(img3_2_2));
                logger.info("insert data: " + imageRepository.save(img3_2_3));
                logger.info("insert data: " + imageRepository.save(img3_3_0));
                logger.info("insert data: " + imageRepository.save(img3_3_1));
                logger.info("insert data: " + imageRepository.save(img3_3_2));
                logger.info("insert data: " + imageRepository.save(img3_3_3));
                logger.info("insert data: " + imageRepository.save(img3_3_4));
                logger.info("insert data: " + imageRepository.save(img3_4_0));
                logger.info("insert data: " + imageRepository.save(img3_4_1));
                logger.info("insert data: " + imageRepository.save(img3_4_2));
                logger.info("insert data: " + imageRepository.save(img3_4_3));
                logger.info("insert data: " + imageRepository.save(img3_4_4));
                logger.info("insert data: " + imageRepository.save(img3_5_0));
                logger.info("insert data: " + imageRepository.save(img3_5_1));
                logger.info("insert data: " + imageRepository.save(img3_5_2));
                logger.info("insert data: " + imageRepository.save(img3_5_3));
                logger.info("insert data: " + imageRepository.save(img3_5_4));
                logger.info("insert data: " + imageRepository.save(img3_5_5));

                //Hotel4
                Hotel ho4 = new Hotel("Louis Mo Apartment",
                        """
                                Tọa lạc tại thành phố Đà Nẵng, Louis Mo Apartment có chỗ nghỉ, xe đạp cho khách sử dụng miễn phí, hồ bơi ngoài trời, trung tâm thể dục, quầy bar, sảnh khách chung và tầm nhìn ra quang cảnh thành phố. Chỗ nghỉ này cung cấp WiFi miễn phí trong toàn bộ khuôn viên.
                                                
                                Tất cả các căn tại đây đều có máy điều hòa, TV màn hình phẳng, phòng khách với ghế sofa, bếp ăn đầy đủ tiện nghi với khu vực ăn uống và phòng tắm riêng đi kèm vòi sen, dép đi trong phòng cùng máy sấy tóc. Các căn còn được trang bị lò vi sóng, tủ lạnh, minibar và ấm đun nước.
                                                
                                Căn hộ cung cấp dịch vụ giặt là cùng các tiện nghi văn phòng như máy fax và máy photocopy.
                                                
                                Du khách có thể thư giãn trên sân hiên phơi nắng tại Louis Mo Apartment.
                                                
                                Chỗ nghỉ nằm cách Bãi biển Mỹ Khê 400 m và Bãi biển Bắc Mỹ An 1,1 km. Sân bay gần nhất là sân bay quốc tế Đà Nẵng, nằm trong bán kính 6 km từ Louis Mo Apartment.""",
                        2,
                        "20 An Thượng 28, Đà Nẵng, Việt Nam",
                        "1 hồ bơi, " +
                                "Xe đưa đón sân bay, " +
                                "Phòng không hút thuốc, " +
                                "Trung tâm thể dục, " +
                                "Dịch vụ phòng, " +
                                "WiFi có ở mọi khu vực, " +
                                "Wi-Fi miễn phí, " +
                                "Phòng gia đình, " +
                                "Quầy bar",
                        4,
                        false,
                        3.3,
                        false,
                        true,
                        "long@gmail.com");

                logger.info("insert data: " + hotelRepository.save(ho4));

                Room room4_1 = new Room(4, "75 m²", 6_248_700.0, 6, 1_687_149.0, 0, 3, 2, 2, "Căn hộ Deluxe","Căn hộ nguyên căn,Bếp riêng,Phòng tắm riêng trong phòng,Ban công,Nhìn ra thành phố,Hồ bơi có tầm nhìn,Hồ bơi trên sân thượng,Điều hòa không khí,TV màn hình phẳng,Hệ thống cách âm,Minibar,WiFi miễn phí",
                    "Đồ vệ sinh cá nhân miễn phí,Nhà vệ sinh,Bồn tắm,Vòi sen,Khăn tắm,Dép,Toilet chung,Máy sấy tóc,Toilet phụ,Giấy vệ sinh",
                    "Nhìn ra thành phố,Nhìn ra biển","Các tầng trên đi lên bằng thang máy,Các tầng trên chỉ lên được bằng cầu thang,Ra trải giường,Tủ hoặc phòng để quần áo,Căn hộ riêng trong tòa nhà,Nước rửa tay, Minibar,Điều hòa không khí, Sàn lát gỗ,Không gây dị ứng,Sàn lát gạch/đá cẩm thạch,Hệ thống cách âm,Bàn ủi, Quạt máy,Sàn trải thảm,Thiết bị báo carbon monoxide,Tủ lạnh,Lò vi sóng,Ấm đun nước điện,Bếp nấu,Đồ bếp,Bàn ăn,Bếp,Bếp nhỏ,Ghế sofa,Bàn làm việc,Khu vực tiếp khách,Khu vực phòng ăn,TV,TV màn hình phẳng,Truyền hình cáp,Ban công,Ổ điện gần giường,Giá treo quần áo,Giường xếp,Giá phơi quần áo,Giường sofa,Máy điều hòa độc lập cho từng phòng",
                    true);
                Room room4_2 = new Room(4, "75 m²", 6_224_400.0, 6, 1_680_588.0, 2, 2, 2, 2, "Căn Hộ Superior","Căn hộ nguyên căn,Bếp riêng,Phòng tắm riêng trong phòng,Ban công,Nhìn ra thành phố,Hồ bơi có tầm nhìn,Hồ bơi trên sân thượng,Điều hòa không khí,TV màn hình phẳng,Hệ thống cách âm,Minibar,WiFi miễn phí",
                    "Đồ vệ sinh cá nhân miễn phí,Nhà vệ sinh,Bồn tắm,Vòi sen,Khăn tắm,Dép,Toilet chung,Máy sấy tóc,Toilet phụ,Giấy vệ sinh",
                    "Nhìn ra thành phố,Nhìn ra biển","Các tầng trên đi lên bằng thang máy,Các tầng trên chỉ lên được bằng cầu thang,Ra trải giường,Tủ hoặc phòng để quần áo,Căn hộ riêng trong tòa nhà,Nước rửa tay, Minibar,Điều hòa không khí, Sàn lát gỗ,Không gây dị ứng,Sàn lát gạch/đá cẩm thạch,Hệ thống cách âm,Bàn ủi, Quạt máy,Sàn trải thảm,Thiết bị báo carbon monoxide,Tủ lạnh,Lò vi sóng,Ấm đun nước điện,Bếp nấu,Đồ bếp,Bàn ăn,Bếp,Bếp nhỏ,Ghế sofa,Bàn làm việc,Khu vực tiếp khách,Khu vực phòng ăn,TV,TV màn hình phẳng,Truyền hình cáp,Ban công,Ổ điện gần giường,Giá treo quần áo,Giường xếp,Giá phơi quần áo,Giường sofa,Máy điều hòa độc lập cho từng phòng",
                    true);
                Room room4_3 = new Room(4, "75 m²", 6_318_000.0, 6, 1_705_860.0, 0, 3, 2, 2, "Căn Hộ 2 Phòng Ngủ","Căn hộ nguyên căn,Bếp riêng,Phòng tắm riêng trong phòng,Ban công,Nhìn ra thành phố,Hồ bơi có tầm nhìn,Hồ bơi trên sân thượng,Điều hòa không khí,TV màn hình phẳng,Hệ thống cách âm,Minibar,WiFi miễn phí",
                    "Đồ vệ sinh cá nhân miễn phí,Nhà vệ sinh,Bồn tắm,Vòi sen,Khăn tắm,Dép,Toilet chung,Máy sấy tóc,Toilet phụ,Giấy vệ sinh",
                    "Nhìn ra thành phố,Nhìn ra biển","Các tầng trên đi lên bằng thang máy,Các tầng trên chỉ lên được bằng cầu thang,Ra trải giường,Tủ hoặc phòng để quần áo,Căn hộ riêng trong tòa nhà,Nước rửa tay, Minibar,Điều hòa không khí, Sàn lát gỗ,Không gây dị ứng,Sàn lát gạch/đá cẩm thạch,Hệ thống cách âm,Bàn ủi, Quạt máy,Sàn trải thảm,Thiết bị báo carbon monoxide,Tủ lạnh,Lò vi sóng,Ấm đun nước điện,Bếp nấu,Đồ bếp,Bàn ăn,Bếp,Bếp nhỏ,Ghế sofa,Bàn làm việc,Khu vực tiếp khách,Khu vực phòng ăn,TV,TV màn hình phẳng,Truyền hình cáp,Ban công,Ổ điện gần giường,Giá treo quần áo,Giường xếp,Giá phơi quần áo,Giường sofa,Máy điều hòa độc lập cho từng phòng",
                    true);
                Room room4_4 = new Room(4, "110 m²", 6_300_000.0, 4, 1_701_000.0, 0, 3, 1, 1, "Căn Hộ 2 Tầng","Căn hộ nguyên căn,Bếp riêng,Phòng tắm riêng trong phòng,Ban công,Nhìn ra thành phố,Hồ bơi có tầm nhìn,Hồ bơi trên sân thượng,Điều hòa không khí,TV màn hình phẳng,Hệ thống cách âm,Minibar,WiFi miễn phí",
                    "Đồ vệ sinh cá nhân miễn phí,Nhà vệ sinh,Bồn tắm,Vòi sen,Khăn tắm,Dép,Toilet chung,Máy sấy tóc,Toilet phụ,Giấy vệ sinh",
                    "Nhìn ra thành phố,Nhìn ra biển","Các tầng trên đi lên bằng thang máy,Các tầng trên chỉ lên được bằng cầu thang,Ra trải giường,Tủ hoặc phòng để quần áo,Căn hộ riêng trong tòa nhà,Nước rửa tay, Minibar,Điều hòa không khí, Sàn lát gỗ,Không gây dị ứng,Sàn lát gạch/đá cẩm thạch,Hệ thống cách âm,Bàn ủi, Quạt máy,Sàn trải thảm,Thiết bị báo carbon monoxide,Tủ lạnh,Lò vi sóng,Ấm đun nước điện,Bếp nấu,Đồ bếp,Bàn ăn,Bếp,Bếp nhỏ,Ghế sofa,Bàn làm việc,Khu vực tiếp khách,Khu vực phòng ăn,TV,TV màn hình phẳng,Truyền hình cáp,Ban công,Ổ điện gần giường,Giá treo quần áo,Giường xếp,Giá phơi quần áo,Giường sofa,Máy điều hòa độc lập cho từng phòng",
                    true);

                logger.info("insert data: " + roomRepository.save(room4_1));
                logger.info("insert data: " + roomRepository.save(room4_2));
                logger.info("insert data: " + roomRepository.save(room4_3));
                logger.info("insert data: " + roomRepository.save(room4_4));

                Review re4_1 = new Review(4, "hung@gmail.com", "Ks rất đẹp. Anh chủ rất nhiệt tình. Cảm ơn anh nhiều", 10);
                Review re4_2 = new Review(4, "truong@gmail.com", "Phòng ốc rộng rãi và sạch sẽ, vị trí tuyệt vời. Gần biển và gần nhiều dịch vụ ăn uống, mua sắm và giải trí.", 8);
                Review re4_3 = new Review(4, "tran@gmail.com", "Khách sạn mới xây, sạch sẽ. Anh chủ đẹp trai, nhẹ nhàng lắm lun :)", 10);
                Review re4_4 = new Review(4, "gia@gmail.com", "Khách sạn mới xây.. thiết kế đẹp và sang trọng. phòng tốt. hồ bơi tuyệt vời. Sẽ quay lại lần sau.", 10);
                Review re4_5 = new Review(4, "thanh@gmail.com", "Căn hộ rộng rãi cho 4 người chúng tôi. Phòng rất sạch sẽ, có đầy đủ lò vi sóng và bếp từ để chúng tôi hâm nóng thức ăn.", 10);

                logger.info("insert data: " + reviewRepository.save(re4_1));
                logger.info("insert data: " + reviewRepository.save(re4_2));
                logger.info("insert data: " + reviewRepository.save(re4_3));
                logger.info("insert data: " + reviewRepository.save(re4_4));
                logger.info("insert data: " + reviewRepository.save(re4_5));

                Image img4_0_0 = new Image(4, 0, "https://res.cloudinary.com/dei0obeww/image/upload/v1671898441/npbooking/Louis%20Mo%20Apartment/351302984_tmxaxf.jpg");
                Image img4_0_1 = new Image(4, 0, "https://res.cloudinary.com/dei0obeww/image/upload/v1671898441/npbooking/Louis%20Mo%20Apartment/351302956_nmqfnp.jpg");
                Image img4_0_2 = new Image(4, 0, "https://res.cloudinary.com/dei0obeww/image/upload/v1671898441/npbooking/Louis%20Mo%20Apartment/356658269_ydufsd.jpg");
                Image img4_0_3 = new Image(4, 0, "https://res.cloudinary.com/dei0obeww/image/upload/v1671898441/npbooking/Louis%20Mo%20Apartment/351302932_xsklog.jpg");
                Image img4_0_4 = new Image(4, 0, "https://res.cloudinary.com/dei0obeww/image/upload/v1671898441/npbooking/Louis%20Mo%20Apartment/374974952_kr1nar.jpg");
                Image img4_0_5 = new Image(4, 0, "https://res.cloudinary.com/dei0obeww/image/upload/v1671898441/npbooking/Louis%20Mo%20Apartment/351302992_y91dbg.jpg");
                Image img4_1_0 = new Image(4, 1, "https://res.cloudinary.com/dei0obeww/image/upload/v1671943486/npbooking/Louis%20Mo%20Apartment/10_zt56z2.jpg");
                Image img4_1_1 = new Image(4, 1, "https://res.cloudinary.com/dei0obeww/image/upload/v1671943486/npbooking/Louis%20Mo%20Apartment/11_vval1s.jpg");
                Image img4_1_2 = new Image(4, 1, "https://res.cloudinary.com/dei0obeww/image/upload/v1671943485/npbooking/Louis%20Mo%20Apartment/12_oantbf.jpg");
                Image img4_1_3 = new Image(4, 1, "https://res.cloudinary.com/dei0obeww/image/upload/v1671943486/npbooking/Louis%20Mo%20Apartment/13_gu2uyy.jpg");
                Image img4_1_4 = new Image(4, 1, "https://res.cloudinary.com/dei0obeww/image/upload/v1671943486/npbooking/Louis%20Mo%20Apartment/14_nbcjei.jpg");
                Image img4_1_5 = new Image(4, 1, "https://res.cloudinary.com/dei0obeww/image/upload/v1671943486/npbooking/Louis%20Mo%20Apartment/15_wotdbd.jpg");
                Image img4_2_0 = new Image(4, 2, "https://res.cloudinary.com/dei0obeww/image/upload/v1671943486/npbooking/Louis%20Mo%20Apartment/20_a6xair.jpg");
                Image img4_2_1 = new Image(4, 2, "https://res.cloudinary.com/dei0obeww/image/upload/v1671943486/npbooking/Louis%20Mo%20Apartment/21_aztyql.jpg");
                Image img4_2_2 = new Image(4, 2, "https://res.cloudinary.com/dei0obeww/image/upload/v1671943486/npbooking/Louis%20Mo%20Apartment/22_pq5msm.jpg");
                Image img4_2_3 = new Image(4, 2, "https://res.cloudinary.com/dei0obeww/image/upload/v1671943486/npbooking/Louis%20Mo%20Apartment/23_jx7wwa.jpg");
                Image img4_2_4 = new Image(4, 2, "https://res.cloudinary.com/dei0obeww/image/upload/v1671943486/npbooking/Louis%20Mo%20Apartment/24_zialbw.jpg");
                Image img4_2_5 = new Image(4, 2, "https://res.cloudinary.com/dei0obeww/image/upload/v1671943486/npbooking/Louis%20Mo%20Apartment/25_bculbn.jpg");
                Image img4_3_0 = new Image(4, 3, "https://res.cloudinary.com/dei0obeww/image/upload/v1671943487/npbooking/Louis%20Mo%20Apartment/30_bifavc.jpg");
                Image img4_3_1 = new Image(4, 3, "https://res.cloudinary.com/dei0obeww/image/upload/v1671943487/npbooking/Louis%20Mo%20Apartment/31_mxczug.jpg");
                Image img4_3_2 = new Image(4, 3, "https://res.cloudinary.com/dei0obeww/image/upload/v1671943487/npbooking/Louis%20Mo%20Apartment/32_ilphyq.jpg");
                Image img4_3_3 = new Image(4, 3, "https://res.cloudinary.com/dei0obeww/image/upload/v1671943487/npbooking/Louis%20Mo%20Apartment/33_txfgmi.jpg");
                Image img4_3_4 = new Image(4, 3, "https://res.cloudinary.com/dei0obeww/image/upload/v1671943487/npbooking/Louis%20Mo%20Apartment/34_vqs3cc.jpg");
                Image img4_3_5 = new Image(4, 3, "https://res.cloudinary.com/dei0obeww/image/upload/v1671943487/npbooking/Louis%20Mo%20Apartment/35_qi7atl.jpg");
                Image img4_4_0 = new Image(4, 4, "https://res.cloudinary.com/dei0obeww/image/upload/v1671943487/npbooking/Louis%20Mo%20Apartment/40_rxom7d.jpg");
                Image img4_4_1 = new Image(4, 4, "https://res.cloudinary.com/dei0obeww/image/upload/v1671943487/npbooking/Louis%20Mo%20Apartment/41_yr6zad.jpg");
                Image img4_4_2 = new Image(4, 4, "https://res.cloudinary.com/dei0obeww/image/upload/v1671943487/npbooking/Louis%20Mo%20Apartment/42_nuykuc.jpg");
                Image img4_4_3 = new Image(4, 4, "https://res.cloudinary.com/dei0obeww/image/upload/v1671943487/npbooking/Louis%20Mo%20Apartment/43_kbkm4f.jpg");
                Image img4_4_4 = new Image(4, 4, "https://res.cloudinary.com/dei0obeww/image/upload/v1671943487/npbooking/Louis%20Mo%20Apartment/44_ah97k1.jpg");
                Image img4_4_5 = new Image(4, 4, "https://res.cloudinary.com/dei0obeww/image/upload/v1671943488/npbooking/Louis%20Mo%20Apartment/45_o8i1fq.jpg");

                logger.info("insert data: " + imageRepository.save(img4_0_0));
                logger.info("insert data: " + imageRepository.save(img4_0_1));
                logger.info("insert data: " + imageRepository.save(img4_0_2));
                logger.info("insert data: " + imageRepository.save(img4_0_3));
                logger.info("insert data: " + imageRepository.save(img4_0_4));
                logger.info("insert data: " + imageRepository.save(img4_0_5));
                logger.info("insert data: " + imageRepository.save(img4_1_0));
                logger.info("insert data: " + imageRepository.save(img4_1_1));
                logger.info("insert data: " + imageRepository.save(img4_1_2));
                logger.info("insert data: " + imageRepository.save(img4_1_3));
                logger.info("insert data: " + imageRepository.save(img4_1_4));
                logger.info("insert data: " + imageRepository.save(img4_1_5));
                logger.info("insert data: " + imageRepository.save(img4_2_0));
                logger.info("insert data: " + imageRepository.save(img4_2_1));
                logger.info("insert data: " + imageRepository.save(img4_2_2));
                logger.info("insert data: " + imageRepository.save(img4_2_3));
                logger.info("insert data: " + imageRepository.save(img4_2_4));
                logger.info("insert data: " + imageRepository.save(img4_2_5));
                logger.info("insert data: " + imageRepository.save(img4_3_0));
                logger.info("insert data: " + imageRepository.save(img4_3_1));
                logger.info("insert data: " + imageRepository.save(img4_3_2));
                logger.info("insert data: " + imageRepository.save(img4_3_3));
                logger.info("insert data: " + imageRepository.save(img4_3_4));
                logger.info("insert data: " + imageRepository.save(img4_3_5));
                logger.info("insert data: " + imageRepository.save(img4_4_0));
                logger.info("insert data: " + imageRepository.save(img4_4_1));
                logger.info("insert data: " + imageRepository.save(img4_4_2));
                logger.info("insert data: " + imageRepository.save(img4_4_3));
                logger.info("insert data: " + imageRepository.save(img4_4_4));
                logger.info("insert data: " + imageRepository.save(img4_4_5));

            }
        };
    }
}
