package com.example.group15project;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class JUnitTestUploadPhotos {



    /*** AT-I**/
    @Test
    public void photosAreEmpty() {
        assertTrue(UploadPhotosActivity.isEmptyPhoto1(empty_photo));
        assertTrue(UploadPhotosActivity.isEmptyPhoto2(empty_photo));
        assertTrue(UploadPhotosActivity.isEmptyPhoto3(empty_photo));


    }

    /*** AT-II**/
    @Test
    public void onePhotoAdded() {
        assertTrue(UploadPhotosActivity.isPhotoAddedFirst('chair.jpg'));
    }

    /*** AT-III**/
    @Test
    public void removeFirstPhoto() {
        assertTrue(UploadPhotosActivity.isEmptyPhoto1(empty_photo));
    }

}
