package adam.compare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UploadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);


        Map config = new HashMap();
        config.put("cloud_name", "kaifeng");
        config.put("api_key", "414531475172292");
        config.put("api_secret", "aNPLWOqDua6j5p2KDvMwxsUzAh8");
        Cloudinary cloudinary = new Cloudinary(config);

        try {
            cloudinary.uploader().upload("http://res.cloudinary.com/demo/image/upload/sample.jpg", ObjectUtils.emptyMap());
        } catch (IOException e) {

            System.out.print("File not found");
        }

        System.out.println("hello");
    }
}
