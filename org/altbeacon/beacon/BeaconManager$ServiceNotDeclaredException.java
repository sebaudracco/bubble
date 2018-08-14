package org.altbeacon.beacon;

public class BeaconManager$ServiceNotDeclaredException extends RuntimeException {
    final /* synthetic */ BeaconManager this$0;

    public BeaconManager$ServiceNotDeclaredException(BeaconManager this$0) {
        this.this$0 = this$0;
        super("The BeaconService is not properly declared in AndroidManifest.xml.  If using Eclipse, please verify that your project.properties has manifestmerger.enabled=true");
    }
}
