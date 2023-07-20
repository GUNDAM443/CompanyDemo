package com.example.demo.rtree;

import cn.hutool.json.JSONUtil;
import com.github.davidmoten.rtree.Entry;
import com.github.davidmoten.rtree.RTree;
import com.github.davidmoten.rtree.geometry.*;
import rx.Observable;

import java.util.ArrayList;
import java.util.List;

import static com.github.davidmoten.rtree.geometry.Geometries.point;

public class Rtree {
    //北京市东城区和西城区的边界，这里只展示部分数据，非完全边界数据
    private String dongcheng = "MULTIPOLYGON(((116.38059 39.871148,116.399097 39.872205,116.397612 39.898675,1116.38059 39.871148)))";
    private String xicheng = "MULTIPOLYGON(((116.387658 39.96093,116.38678 39.957014,116.393346 39.957355,116.387658 39.96093)))";

//    private RTree<String, Rectangle> secondTree = RTree.minChildren(3).maxChildren(6).create();

//    public void build() {
//        List<CityDTO> sourceData = buildCityDTOs();
//        //1.对每个多边形,存入所有边构建一级R树
//        for (CityDTO sourceDatum : sourceData) {
//            RTree<String, Line> tree = RTree.minChildren(3).maxChildren(6).create();
//
//            List<List<Double>> polygon = GeoHelper.transfer2List(sourceDatum.getShape());
//            for (int i = 0; i < polygon.size(); i++) {
//                List<Double> nextPoints = polygon.get((i + 1) % polygon.size());
//                List<Double> points = polygon.get(i);
//                Double lng1 = points.get(0);
//                Double lat1 = points.get(1);
//                Double lng2 = nextPoints.get(0);
//                Double lat2 = nextPoints.get(1);
//                tree = tree.add(String.valueOf(i), Geometries.line(lng1, lat1, lng2, lat2));
//            }
//            //2. 将每个多边形的外接矩形构造为二级R树
//            secondTree = secondTree.add(sourceDatum.getName(), tree.mbr().get());
//        }
//    }

    public static void main(String[] args) {
//        //rtree 存点 查询一个多边形内命中多少点
//        RTree<String, Point> tree1 = RTree.maxChildren(5).create();
//        tree1 = tree1.add("DAVE", point(10, 20))
//                .add("FRED", point(12, 25))
//                .add("MARY", point(97, 125));
//
//        Observable<Entry<String, Point>> entries1 =
//                tree1.search(Geometries.rectangle(8, 15, 30, 35));
//        entries1.toBlocking().forEach(e->{
//
//            System.out.println(e.value());
//            System.out.println(e.geometry());
//        });

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
    private static boolean isInPolygon(PointDto pointDTO, List<List<Double>> polygon) {
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
