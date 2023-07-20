package com.example.demo.rtree;

import com.github.davidmoten.rtree.Entry;
import com.github.davidmoten.rtree.RTree;
import com.github.davidmoten.rtree.geometry.Geometries;
import com.github.davidmoten.rtree.geometry.Line;
import com.github.davidmoten.rtree.geometry.Rectangle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import rx.Observable;

import java.util.ArrayList;
import java.util.List;

import static com.github.davidmoten.rtree.geometry.Geometries.point;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RtreeTest {
    @Test
    public void test01(){
        //rtree  查询点在多边形内（将多边形索引成rtree最小矩形）
        //先将每条边存入rtree
        RTree<String, Line> lineRTree = RTree.create();
        lineRTree = lineRTree.add("多边形A-边1", Geometries.line(1, 1, 2, 5));
        lineRTree = lineRTree.add("多边形A-边2", Geometries.line(1, 1, 5, 2));
        lineRTree = lineRTree.add("多边形A-边3", Geometries.line(5, 2, 6, 6));
        lineRTree = lineRTree.add("多边形A-边4", Geometries.line(6, 6, 2, 5));
        //再将四条边存入rtree  组成最小矩形
        RTree<String, Rectangle> polyonTree = RTree.create();
        polyonTree = polyonTree.add("多边形A", lineRTree.mbr().get());

        //查询点是否在最小矩形内
        //这个点在多边形内
        Observable<Entry<String, Rectangle>> search = polyonTree.search(point(3, 3));
        search.forEach(e->{
            System.out.println(e.value());
            System.out.println(e.geometry());
        });
        //这个点在最小矩形内  并不在多边形内
        Observable<Entry<String, Rectangle>> search2 = polyonTree.search(point(1, 3));
        search2.forEach(e->{
            System.out.println(e.value());
            System.out.println(e.geometry());
        });
        //这个点不在最小矩形内
        Observable<Entry<String, Rectangle>> search3 = polyonTree.search(point(6, 7));
        search3.forEach(e->{
            System.out.println(e.value());
            System.out.println(e.geometry());
        });
        //在最小矩形内  不代表在最小多边形内  通过射线法计算一下 确定最终结果
        List<List<Double>> polygon = new ArrayList<>();
        List<Double> point = new ArrayList<>();
        point.add(1.0);
        point.add(1.0);
        polygon.add(point);
        List<Double> point2 = new ArrayList<>();
        point2.add(2.0);
        point2.add(5.0);
        polygon.add(point2);
        List<Double> point3 = new ArrayList<>();
        point3.add(5.0);
        point3.add(2.0);
        polygon.add(point3);
        List<Double> point4 = new ArrayList<>();
        point4.add(6.0);
        point4.add(6.0);
        polygon.add(point4);
        boolean inPolygon1 = isInPolygon(new PointDto(3.0, 3.0), polygon);
        boolean inPolygon2 = isInPolygon(new PointDto(1.0, 3.0), polygon);
        boolean inPolygon3 = isInPolygon(new PointDto(6.0, 7.0), polygon);
        System.out.println(inPolygon1);
        System.out.println(inPolygon2);
        System.out.println(inPolygon3);

    }

    /**
     * 射线法判断点是否在多边形内
     * @param pointDTO
     * @param polygon
     * @return
     */
    private boolean isInPolygon(PointDto pointDTO, List<List<Double>> polygon) {
        int nCross = 0;
        for (int i = 0; i < polygon.size(); i++) {
            List<Double> p1 = polygon.get(i);
            List<Double> p2 = polygon.get((i + 1) % polygon.size());
            Double lng1 = p1.get(0);
            Double lat1 = p1.get(1);
            Double lng2 = p2.get(0);
            Double lat2 = p2.get(1);
            //p1p2 与 y = p0.y平行
            if (lng1.equals(lng2)) {
                continue;
            }
            //交点在p1p2的延长线上
            if (pointDTO.getLng() < Math.min(lng1, lng2)) {
                continue;
            }
            //交点在p1p2的延长线上
            if (pointDTO.getLng() >= Math.max(lng1, lng2)) {
                continue;
            }
            // 求交点的X坐标
            double x = (pointDTO.getLng() - lng1) * (lat2 - lat1) / (lng2 - lng1) + lat1;
            if (x > pointDTO.getLat()) {
                //只统计单边
                nCross++;
            }
        }
        //单边交点为奇数，点在多边形内
        return (nCross % 2 == 1);
    }
}
