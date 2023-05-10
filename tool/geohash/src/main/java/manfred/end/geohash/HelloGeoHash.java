package manfred.end.geohash;

import ch.hsr.geohash.GeoHash;

public class HelloGeoHash {

    public static void main(String[] args) {
        GeoHash geohash = GeoHash.withCharacterPrecision(53.244664, -6.140530, 12);
        GeoHash geohash2 = GeoHash.withCharacterPrecision(55.244664, -6.140530, 12);
        String geohashString = geohash.toBase32().substring(0, 3); //3 characters for around 100km of precision
        String geohashString2 = geohash2.toBase32().substring(0, 3); //3 characters for around 100km of precision

        System.out.println(geohashString + " : " + geohashString2);
    }
}
