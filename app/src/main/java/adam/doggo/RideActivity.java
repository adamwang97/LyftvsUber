package adam.doggo;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.lyft.lyftbutton.LyftButton;
import com.lyft.lyftbutton.RideParams;
import com.lyft.lyftbutton.RideTypeEnum;
import com.lyft.networking.ApiConfig;
import com.uber.sdk.android.core.UberSdk;
import com.uber.sdk.android.rides.RideParameters;
import com.uber.sdk.android.rides.RideRequestButton;
import com.uber.sdk.core.auth.Scope;
import com.uber.sdk.rides.client.SessionConfiguration;

import java.io.IOException;
import java.util.Arrays;

public class RideActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride);

        ApiConfig apiConfig = new ApiConfig.Builder()
                .setClientId("mTqxAnXGGA7J")
                .setClientToken("gAAAAABX8GpUjSv4lKh_UY6gAZZMQmBA7-e6I8zeDfFrxqsq8tWg1UNWOSi_oe9BoLoS70_SWX_7OF-INzI7QmG8ueHmKkgb52WWaeLCdIPA_1vu9wFm2LMU574xLOVyOT4mwxbxtmpqrwms7wiYxbn9cTZkmR6cutsArL62y75sug0KTzMOS-0=")
                .build();
/*
        SessionConfiguration config = new SessionConfiguration.Builder()
                // mandatory
                .setClientId("YOUR_CLIENT_ID")
                // required for enhanced button features
                .setServerToken("YOUR_SERVER_TOKEN")
                // required for implicit grant authentication
                .setRedirectUri("YOUR_REDIRECT_URI")
                // required scope for Ride Request Widget features
                .setScopes(Arrays.asList(Scope.RIDE_WIDGETS))
                // optional: set Sandbox as operating environment
                .setEnvironment(SessionConfiguration.Environment.SANDBOX)
                .build();

        UberSdk.initialize(config);

        RideRequestButton requestButton = (RideRequestButton) findViewById(R.id.button8); */
        LyftButton lyftButton = (LyftButton) findViewById(R.id.button5);
        lyftButton.setApiConfig(apiConfig);

        TextView from = (TextView) findViewById(R.id.textFrom);
        TextView to = (TextView) findViewById(R.id.textTo);

        Bundle extras = getIntent().getExtras();

        if ( extras != null) {

            Geocoder geocoder = new Geocoder(this);

            Location location = (Location) extras.get("FROM");
            Address toAddr = (Address) extras.get("TO");
            Address fromAddr;

            //System.out.println(toAddr.getFeatureName());

            try {


                fromAddr = geocoder.getFromLocation(location.getLatitude(),
                    location.getLongitude(), 1).get(0);
                from.append(fromAddr.getAddressLine(0));
                to.append(toAddr.getAddressLine(0));
            } catch(IOException e) {
                System.out.println("FILE NOT FOUND");
                e.printStackTrace();
            }

            RideParams.Builder rideParamsBuilder = new RideParams.Builder()
                    .setPickupLocation(location.getLongitude(), location.getLatitude())
                    .setDropoffLocation(toAddr.getLongitude(), toAddr.getLatitude());
            rideParamsBuilder.setRideTypeEnum(RideTypeEnum.CLASSIC);
/*
            Uber button
            RideParameters rideParams = new RideParameters.Builder()
                    .setProductId("a1111c8c-c720-46c3-8534-2fcdd730040d")
                    .setPickupToMyLocation()
                    .setDropoffLocation(37.795079, -122.4397805, "Embarcadero", "One Embarcadero Center, San Francisco")
                    .build();
            requestButton.setRideParameters(rideParams);
*/
            lyftButton.setRideParams(rideParamsBuilder.build());
            lyftButton.load();
        }
    }
}
