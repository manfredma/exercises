package manfred.end.ascii.graph;

import com.indvd00m.ascii.render.Region;
import com.indvd00m.ascii.render.Render;
import com.indvd00m.ascii.render.api.ICanvas;
import com.indvd00m.ascii.render.api.IContextBuilder;
import com.indvd00m.ascii.render.api.IRender;
import com.indvd00m.ascii.render.elements.Rectangle;
import com.indvd00m.ascii.render.elements.plot.Axis;
import com.indvd00m.ascii.render.elements.plot.AxisLabels;
import com.indvd00m.ascii.render.elements.plot.Plot;
import com.indvd00m.ascii.render.elements.plot.api.IPlotPoint;
import com.indvd00m.ascii.render.elements.plot.misc.PlotPoint;
import java.util.ArrayList;
import java.util.List;

public class HelloAsciiRender {
    public static void main(String[] args) {
        List<IPlotPoint> points = new ArrayList<>();
        for (int degree = 0; degree <= 360; degree++) {
            if (degree > 75 && degree < 105) {
                continue;
            }
            if (degree > 255 && degree < 285) {
                continue;
            }
            double val = Math.tan(Math.toRadians(degree));
            IPlotPoint plotPoint = new PlotPoint(degree, val);
            points.add(plotPoint);
        }
        IRender render = new Render();
        IContextBuilder builder = render.newBuilder();
        builder.width(80).height(20);
        builder.element(new Rectangle(0, 0, 80, 20));
        builder.layer(new Region(1, 1, 78, 18));
        builder.element(new Axis(points, new Region(0, 0, 78, 18)));
        builder.element(new AxisLabels(points, new Region(0, 0, 78, 18)));
        builder.element(new Plot(points, new Region(0, 0, 78, 18)));
        ICanvas canvas = render.render(builder.build());
        String s = canvas.getText();
        System.out.println(s);
    }
}
