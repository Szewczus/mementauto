package com.auto.mementauto.services;

import com.auto.mementauto.dtos.VehicleDto;
import com.auto.mementauto.entities.UserEntity;
import com.auto.mementauto.entities.VehicleEntity;
import com.auto.mementauto.repositories.UserRepository;
import com.auto.mementauto.repositories.VehicleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private UserRepository userRepository;

    private Logger logger = LoggerFactory.getLogger(VehicleService.class);

    public List<VehicleEntity> showUserVehicles(){
        UserEntity userFromSession = userRepository.findUserEntityByLogin(getLoginFromSession());
        List<VehicleEntity> vehicleEntities = vehicleEntitiesWithCarPictures(vehicleRepository.findVehicleEntitiesByOwner(userFromSession));
        return vehicleEntities;
    }

    public VehicleEntity showVehicleById(Long id) {
        VehicleEntity vehicleEntity = vehicleRepository.findVehicleEntityById(id);

        VehicleEntity vehicle = new VehicleEntity();

        try {
            vehicle = vehicleEntityWithCarPictureBytes(vehicleEntity);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

        return vehicle;
    }

//    public List<VehicleEntity> showUserVehicles(){
//        UserEntity userFromSession = userRepository.findUserEntityByLogin("ania1");
//        return vehicleRepository.findVehicleEntitiesByOwner(userFromSession);
//    }

    public VehicleEntity saveVahicle(VehicleDto vehicleDto){

        VehicleEntity vehicle = vehicleDtoToVehicleEntity(vehicleDto);
        VehicleEntity vehicleWithId = vehicleRepository.save(vehicle);

        try {
            vehicleWithId.setCarPicture(saveByteArrayImage(vehicleDto.getCarPicture(), vehicleWithId));
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

        VehicleEntity vehicleEntity = new VehicleEntity();
        try {
            vehicleEntity = vehicleEntityWithCarPictureBytes(vehicleRepository.save(vehicleWithId));
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

        return vehicleEntity;
    }

    public VehicleEntity changeOwner(VehicleDto vehicleDto){
        VehicleEntity vehicle = vehicleRepository.findVehicleEntityById(vehicleDto.getId());
        UserEntity newOwner = userRepository.findUserEntityById(vehicleDto.getOwner());
        vehicle.setOwner(newOwner);
        VehicleEntity vehicleWithId = vehicleRepository.save(vehicle);
        return vehicleWithId;
    }

    public VehicleEntity editVehicle(VehicleDto vehicleDto){
        VehicleEntity vehicle = vehicleDtoToVehicleEntity(vehicleDto);
        VehicleEntity vehicleWithId = vehicleRepository.save(vehicle);

        try {
            vehicleWithId.setCarPicture(saveByteArrayImage(vehicleDto.getCarPicture(), vehicleWithId));
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        VehicleEntity vehicleEntity = new VehicleEntity();
        try {
            vehicleEntity = vehicleEntityWithCarPictureBytes(vehicleRepository.save(vehicleWithId));
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

        return vehicleEntity;
    }


    private VehicleEntity vehicleDtoToVehicleEntity(VehicleDto vehicleDto) {
        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setId(vehicleDto.getId());
        vehicle.setBrand(vehicleDto.getBrand());
        vehicle.setMileage(vehicleDto.getMileage());
        vehicle.setModel(vehicleDto.getModel());
        //wyciagmiecie ownera po loginie z sesji:
        UserEntity userFromSession = userRepository.findUserEntityByLogin(getLoginFromSession());
        vehicle.setOwner(userFromSession);
        vehicle.setProductionYear(vehicleDto.getProductionYear());
        return vehicle;
    }

    private String getLoginFromSession(){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        String loginFromSession = authentication.getPrincipal().toString();
        return loginFromSession;
    }

    private String saveByteArrayImage(String bytes64, VehicleEntity vehicleEntity) throws IOException {
        byte[] bytes = Base64.getDecoder().decode(bytes64);
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        BufferedImage bImage = ImageIO.read(bis);
        String path = "src\\main\\resources\\pictures\\" + vehicleEntity.getId()+ "_" + vehicleEntity.getBrand() + "_" + vehicleEntity.getModel() + ".png";
        //String path = "output.png";
        ImageIO.write(bImage, "png", new File(path));
        return path;
    }

    private String getImageBytes(String path) throws IOException {
        if (path == null || path.isEmpty()){
            return null;
        }
        BufferedImage bImage = ImageIO.read(new File(path));
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(bImage, "png", bos);
        byte [] data = bos.toByteArray();
        return Base64.getEncoder().encodeToString(data);
    }

    private List<VehicleEntity> vehicleEntitiesWithCarPictures(List<VehicleEntity> vehicleEntities){
        List<VehicleEntity> vehiclesWithPictures = new ArrayList<>();
        try{
            for (VehicleEntity vehicle : vehicleEntities) {
                VehicleEntity newVehicle = vehicleEntityWithCarPictureBytes(vehicle);
                vehiclesWithPictures.add(newVehicle);
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return vehiclesWithPictures;
    }

    private VehicleEntity vehicleEntityWithCarPictureBytes(VehicleEntity vehicleEntity) throws IOException {
        if(vehicleEntity.getCarPicture() != null && !vehicleEntity.getCarPicture().isEmpty()){
            vehicleEntity.setCarPicture(getImageBytes(vehicleEntity.getCarPicture()));
        }
        return vehicleEntity;
    }


}
