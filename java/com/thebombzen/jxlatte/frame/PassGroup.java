package com.thebombzen.jxlatte.frame;

import java.io.IOException;

import com.thebombzen.jxlatte.frame.lfglobal.GlobalModular;
import com.thebombzen.jxlatte.frame.modular.ModularChannel;
import com.thebombzen.jxlatte.frame.modular.ModularStream;
import com.thebombzen.jxlatte.io.Bitreader;

public class PassGroup {
    public final HFCoefficients hfCoefficients;
    public final ModularStream stream;
    public PassGroup(Bitreader reader, Frame frame, int streamIndex,
            ModularChannel[] replacedChannels) throws IOException {
        if (frame.getFrameHeader().encoding == FrameFlags.VARDCT)
            hfCoefficients = new HFCoefficients(reader);
        else
            hfCoefficients = null;
        GlobalModular globalModular = frame.getLFGlobal().gModular;
        stream = new ModularStream(reader, globalModular.globalTree, frame, streamIndex, replacedChannels);
        stream.decodeChannels(reader, false);
        stream.applyTransforms();
    }
}
