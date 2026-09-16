package net.cordicus.raccoons.porting;
//? if >=1.21.11 {

/*import net.minecraft.resources.Identifier;
*///? } else {
import net.minecraft.resources.ResourceLocation;
 //? }
import net.cordicus.raccoons.RaccoonsRabies;
import net.minecraft.resources.ResourceKey;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;


public class RRIdentifier {
    //? if >=1.21.11 {
    /*public Identifier id;
    *///? } else {
    public ResourceLocation id;
     //? }


    //? if >=1.21.11 {
    /*public RRIdentifier(Identifier id) {
    *///? } else {
    public RRIdentifier(ResourceLocation id) {
         //? }
        this.id = id;
    }

    //? if >=1.21.11 {
    /*public static RRIdentifier of(String path) {
        return new RRIdentifier(Identifier.fromNamespaceAndPath(RaccoonsRabies.MOD_ID, path));
    }
    public static RRIdentifier of(ResourceKey path) {
        return new RRIdentifier(path.identifier());
    }
    public static RRIdentifier parse(String path) {
        return new RRIdentifier(Identifier.parse(path));
    }
    public static RRIdentifier ofVanilla(String path) {
        return new RRIdentifier(Identifier.fromNamespaceAndPath("minecraft", path));
    }
    public static RRIdentifier read(StringReader stringReader) throws CommandSyntaxException {
        return new RRIdentifier(Identifier.read(stringReader));
    }
    *///? } else if >1.20.4 {
    public static RRIdentifier of(String path) {
        return new RRIdentifier(ResourceLocation.fromNamespaceAndPath(RaccoonsRabies.MOD_ID, path));
    }
    public static RRIdentifier of(ResourceKey path) {
        return new RRIdentifier(path.location());
    }
    public static RRIdentifier parse(String path) {
        return new RRIdentifier(ResourceLocation.parse(path));
    }
    public static RRIdentifier ofVanilla(String path) {
        return new RRIdentifier(ResourceLocation.fromNamespaceAndPath("minecraft", path));
    }
    public static RRIdentifier read(StringReader stringReader) throws CommandSyntaxException {
        return new RRIdentifier(ResourceLocation.read(stringReader));
    }
    //? } else {

    /*public static RRIdentifier of(ResourceKey path) {
        return new RRIdentifier(path.location());
    }
   public static RRIdentifier of(String path) {
        return new RRIdentifier(ResourceLocation.tryParse(RaccoonsRabies.MOD_ID + ":" + path));
    }
    public static RRIdentifier parse(String path) {
        return new RRIdentifier(ResourceLocation.tryParse(path));
    }
    public static RRIdentifier ofVanilla(String path) {
        return new RRIdentifier(ResourceLocation.tryParse("minecraft:" + path));
    }
    public static RRIdentifier read(StringReader stringReader) throws CommandSyntaxException {
        return new RRIdentifier(ResourceLocation.read(stringReader));
    }
    *///? }
}
