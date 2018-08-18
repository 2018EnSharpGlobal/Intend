package com.ensharp.kimyejin.voicerecognitiontest.MapManager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import java.util.List;

public class ProcedureManager {

    boolean use_elevator;
    boolean use_stair;

    List<Node> path;

    List<Node> floor;
    List<Node> other_floor;

    Navigation underGround_1;
    Navigation underGround_2;
    Navigation underGround_3;

    public ProcedureManager() {
        use_elevator = false;
        use_stair = false;

        // 임의로 생성한 노드
//        Node initialNode = new Node(4, 1);
//        initialNode.setFloor(-1);
//        Node finalNode = new Node(95, 13);
//        finalNode.setFloor(-1);

        path = null;

        floor = null;
        other_floor = null;

        //underGround_1 = new Navigation(MapInfo.map_rows,MapInfo.map_cols ,MapInfo.B1);
        underGround_1 = null;
        underGround_2 = null;
        underGround_3 = null;
//        underGround_1.set_Initail_Final_Node(initialNode, finalNode);
//        underGround_1.setNodes();
//        underGround_1.setInformations(block);
//        underGround_2 = new Navigation(MapInfo.map_rows, MapInfo.map_cols, MapInfo.B2);
//        underGround_3 = new Navigation(MapInfo.map_rows, MapInfo.map_cols, MapInfo.B3);
    }

    //초기 위치부터 목적지 위치까지 경로 리스트 얻기
    public List<Node> detect_Path(String destination) {
        Node initialNode = get_currentNode();
        Node finalNode = get_destinationNode(destination);

        underGround_1 = new Navigation(MapInfo.map_rows, MapInfo.map_cols,MapInfo.B1);
        underGround_2 = new Navigation(MapInfo.map_rows, MapInfo.map_cols,MapInfo.B2);
        underGround_3 = new Navigation(MapInfo.map_rows, MapInfo.map_cols,MapInfo.B3);

        //층 수 가 다를 경우
        if (initialNode.getFloor() != finalNode.getFloor()) {
            Navigation initial_Navi = null;
            Navigation final_Navi = null;

            switch (initialNode.getFloor()) {
                case MapInfo.B1:
                    initial_Navi = underGround_1;
                    break;
                case MapInfo.B2:
                    initial_Navi = underGround_2;
                    break;
                case MapInfo.B3:
                    initial_Navi = underGround_3;
                    break;
            }

            initial_Navi.setInitialNode(get_currentNode());

            switch (finalNode.getFloor()) {
                case MapInfo.B1:
                    final_Navi = underGround_1;
                    break;
                case MapInfo.B2:
                    final_Navi = underGround_2;
                    break;
                case MapInfo.B3:
                    final_Navi = underGround_3;
                    break;
            }

            final_Navi.setFinalNode(finalNode);

            //엘레베이터 선호 할 때
            if (use_elevator) {
                Node better_elevator = null;

                int[] check_Elevator = new int[4];
                for (int i = 0; i < check_Elevator.length; i++) {
                    check_Elevator[i] = 0;
                }
                switch (initialNode.getFloor()) {
                    case MapInfo.B1:
                        check_Elevator[0] = underGround_1.calculate_F(initialNode, underGround_1.getSearchArea()[5][1]);
                        check_Elevator[1] = underGround_1.calculate_F(initialNode, underGround_1.getSearchArea()[5][2]);
                        check_Elevator[2] = underGround_1.calculate_F(initialNode, underGround_1.getSearchArea()[5][3]);
                        check_Elevator[3] = underGround_1.calculate_F(initialNode, underGround_1.getSearchArea()[5][4]);
                        break;
                    case MapInfo.B2:
                        check_Elevator[0] = underGround_2.calculate_F(initialNode, underGround_2.getSearchArea()[5][1]);
                        check_Elevator[1] = underGround_2.calculate_F(initialNode, underGround_2.getSearchArea()[5][2]);
                        check_Elevator[2] = underGround_2.calculate_F(initialNode, underGround_2.getSearchArea()[5][3]);
                        check_Elevator[3] = underGround_2.calculate_F(initialNode, underGround_2.getSearchArea()[5][4]);
                        break;
                    case MapInfo.B3:
                        check_Elevator[0] = underGround_3.calculate_F(initialNode, underGround_3.getSearchArea()[5][1]);
                        check_Elevator[1] = underGround_3.calculate_F(initialNode, underGround_3.getSearchArea()[5][2]);
                        check_Elevator[2] = underGround_3.calculate_F(initialNode, underGround_3.getSearchArea()[5][3]);
                        check_Elevator[3] = underGround_3.calculate_F(initialNode, underGround_3.getSearchArea()[5][4]);
                        break;
                    default:
                        break;
                }

                switch (finalNode.getFloor()) {
                    case MapInfo.B1:
                        check_Elevator[0] = underGround_1.calculate_F(underGround_1.getSearchArea()[5][1], finalNode);
                        check_Elevator[1] = underGround_1.calculate_F(underGround_1.getSearchArea()[5][2], finalNode);
                        check_Elevator[2] = underGround_1.calculate_F(underGround_1.getSearchArea()[5][3], finalNode);
                        check_Elevator[3] = underGround_1.calculate_F(underGround_1.getSearchArea()[5][4], finalNode);
                        break;
                    case MapInfo.B2:
                        check_Elevator[0] = underGround_2.calculate_F(underGround_2.getSearchArea()[5][1], finalNode);
                        check_Elevator[1] = underGround_2.calculate_F(underGround_2.getSearchArea()[5][2], finalNode);
                        check_Elevator[2] = underGround_2.calculate_F(underGround_2.getSearchArea()[5][3], finalNode);
                        check_Elevator[3] = underGround_2.calculate_F(underGround_2.getSearchArea()[5][4], finalNode);
                        break;
                    case MapInfo.B3:
                        check_Elevator[0] = underGround_3.calculate_F(underGround_3.getSearchArea()[5][1], finalNode);
                        check_Elevator[1] = underGround_3.calculate_F(underGround_3.getSearchArea()[5][2], finalNode);
                        check_Elevator[2] = underGround_3.calculate_F(underGround_3.getSearchArea()[5][3], finalNode);
                        check_Elevator[3] = underGround_3.calculate_F(underGround_3.getSearchArea()[5][4], finalNode);
                        break;
                    default:
                        break;
                }

                int find_component = compare_Minimum(check_Elevator);

                switch (find_component + 7) {  //여기서 더하는 7 값은 보정값
                    case MapInfo.elevator_1:
                        better_elevator = underGround_1.getSearchArea()[5][1];
                        break;
                    case MapInfo.elevator_2:
                        better_elevator = underGround_1.getSearchArea()[5][2];
                        break;
                    case MapInfo.elevator_3:
                        better_elevator = underGround_1.getSearchArea()[5][3];
                        break;
                    case MapInfo.elevator_4:
                        better_elevator = underGround_1.getSearchArea()[5][4];
                        break;
                    default:
                        break;
                }

                initial_Navi.set_Initail_Final_Node(initialNode, better_elevator);
                floor = initial_Navi.findPath();
                final_Navi.set_Initail_Final_Node(better_elevator, finalNode);
                other_floor = final_Navi.findPath();

                path = floor;
                path.remove(path.size() - 1);
                path.addAll(other_floor);
            }
            else if (use_stair) {
                Node floor_stair = null;
                Node other_floor_stair = null;

                int[] check_Stair = new int[6];
                for (int i = 0; i < check_Stair.length; i++) {
                    check_Stair[i] = 0;
                }
                switch (initialNode.getFloor()) {
                    case MapInfo.B1:
                        check_Stair[0] = underGround_1.calculate_F(initialNode, underGround_1.getSearchArea()[5][1]);
                        check_Stair[1] = underGround_1.calculate_F(initialNode, underGround_1.getSearchArea()[5][2]);
                        check_Stair[2] = underGround_1.calculate_F(initialNode, underGround_1.getSearchArea()[5][3]);
                        check_Stair[3] = underGround_1.calculate_F(initialNode, underGround_1.getSearchArea()[5][4]);
                        check_Stair[4] = underGround_1.calculate_F(initialNode, underGround_1.getSearchArea()[5][5]);
                        check_Stair[5] = underGround_1.calculate_F(initialNode, underGround_1.getSearchArea()[5][6]);
                        break;
                    case MapInfo.B2:
                        check_Stair[0] = underGround_2.calculate_F(initialNode, underGround_2.getSearchArea()[5][1]);
                        check_Stair[1] = underGround_2.calculate_F(initialNode, underGround_2.getSearchArea()[5][2]);
                        check_Stair[2] = underGround_2.calculate_F(initialNode, underGround_2.getSearchArea()[5][3]);
                        check_Stair[3] = underGround_2.calculate_F(initialNode, underGround_2.getSearchArea()[5][4]);
                        check_Stair[4] = underGround_2.calculate_F(initialNode, underGround_2.getSearchArea()[5][5]);
                        check_Stair[5] = underGround_2.calculate_F(initialNode, underGround_2.getSearchArea()[5][6]);
                        break;
                    case MapInfo.B3:
                        check_Stair[0] = underGround_3.calculate_F(initialNode, underGround_3.getSearchArea()[5][1]);
                        check_Stair[1] = underGround_3.calculate_F(initialNode, underGround_3.getSearchArea()[5][2]);
                        check_Stair[2] = underGround_3.calculate_F(initialNode, underGround_3.getSearchArea()[5][3]);
                        check_Stair[3] = underGround_3.calculate_F(initialNode, underGround_3.getSearchArea()[5][4]);
                        check_Stair[4] = underGround_3.calculate_F(initialNode, underGround_3.getSearchArea()[5][5]);
                        check_Stair[5] = underGround_3.calculate_F(initialNode, underGround_3.getSearchArea()[5][6]);
                        break;
                    default:
                        break;
                }

                switch (finalNode.getFloor()) {
                    case MapInfo.B1:
                        check_Stair[0] = underGround_1.calculate_F(underGround_1.getSearchArea()[5][1], finalNode);
                        check_Stair[1] = underGround_1.calculate_F(underGround_1.getSearchArea()[5][2], finalNode);
                        check_Stair[2] = underGround_1.calculate_F(underGround_1.getSearchArea()[5][3], finalNode);
                        check_Stair[3] = underGround_1.calculate_F(underGround_1.getSearchArea()[5][4], finalNode);
                        check_Stair[4] = underGround_1.calculate_F(underGround_1.getSearchArea()[5][5], finalNode);
                        check_Stair[5] = underGround_1.calculate_F(underGround_1.getSearchArea()[5][6], finalNode);
                        break;
                    case MapInfo.B2:
                        check_Stair[0] = underGround_2.calculate_F(underGround_2.getSearchArea()[5][1], finalNode);
                        check_Stair[1] = underGround_2.calculate_F(underGround_2.getSearchArea()[5][2], finalNode);
                        check_Stair[2] = underGround_2.calculate_F(underGround_2.getSearchArea()[5][3], finalNode);
                        check_Stair[3] = underGround_2.calculate_F(underGround_2.getSearchArea()[5][4], finalNode);
                        check_Stair[4] = underGround_2.calculate_F(underGround_2.getSearchArea()[5][5], finalNode);
                        check_Stair[5] = underGround_2.calculate_F(underGround_2.getSearchArea()[5][6], finalNode);
                        break;
                    case MapInfo.B3:
                        check_Stair[0] = underGround_3.calculate_F(underGround_3.getSearchArea()[5][1], finalNode);
                        check_Stair[1] = underGround_3.calculate_F(underGround_3.getSearchArea()[5][2], finalNode);
                        check_Stair[2] = underGround_3.calculate_F(underGround_3.getSearchArea()[5][3], finalNode);
                        check_Stair[3] = underGround_3.calculate_F(underGround_3.getSearchArea()[5][4], finalNode);
                        check_Stair[4] = underGround_3.calculate_F(underGround_3.getSearchArea()[5][5], finalNode);
                        check_Stair[5] = underGround_3.calculate_F(underGround_3.getSearchArea()[5][6], finalNode);
                        break;
                    default:
                        break;
                }

                int find_component = compare_Minimum(check_Stair);

                switch (find_component + 11) {  //여기서 더하는 11 값은 보정값
                    case MapInfo.stair_1:
                        floor_stair = underGround_1.getSearchArea()[5][1];
                        other_floor_stair = underGround_2.getSearchArea()[6][1];
                        break;
                    case MapInfo.stair_2:
                        floor_stair = underGround_1.getSearchArea()[5][2];
                        other_floor_stair = underGround_2.getSearchArea()[6][2];
                        break;
                    case MapInfo.stair_3:
                        floor_stair = underGround_2.getSearchArea()[5][3];
                        other_floor_stair = underGround_3.getSearchArea()[6][3];
                        break;
                    case MapInfo.stair_4:
                        floor_stair = underGround_2.getSearchArea()[5][4];
                        other_floor_stair = underGround_3.getSearchArea()[6][4];
                        break;
                    case MapInfo.stair_5:
                        floor_stair = underGround_2.getSearchArea()[5][5];
                        other_floor_stair = underGround_3.getSearchArea()[6][5];
                        break;
                    case MapInfo.stair_6:
                        floor_stair = underGround_2.getSearchArea()[5][6];
                        other_floor_stair = underGround_3.getSearchArea()[6][6];
                        break;
                    default:
                        break;
                }

                initial_Navi.set_Initail_Final_Node(initialNode, floor_stair);
                floor = initial_Navi.findPath();
                final_Navi.set_Initail_Final_Node(other_floor_stair, finalNode);
                other_floor = final_Navi.findPath();

                path = floor;
                path.remove(path.size() - 1);
                path.addAll(other_floor);

            }
            else if (!use_elevator && !use_stair) {

            }
        }
        //층 수가 같을 때
        else {

            Navigation temp = null;

            switch (initialNode.getFloor()) {
                case MapInfo.B1:
                    temp = underGround_1;
                    break;
                case MapInfo.B2:
                    temp = underGround_2;
                    break;
                case MapInfo.B3:
                    temp = underGround_3;
                    break;
            }

            temp.set_Initail_Final_Node(initialNode, finalNode);
            path = temp.findPath();

        }

        //경로 로그에 출력하기
        for (Node node : path) {
            Log.e("Floor", String.valueOf(node.getFloor()) + node.toString());
        }
        return path;
    }

    //노드가 가는 경로가 잘 가고 있는지 체크하기
    public boolean user_CheckPosition(List<Node> path, int user_row, int user_col) {
        boolean check_Position = false;
        for (Node node : path) {
            if (node.getRow() - 1 >= 0 &&
                    node.getCol() - 1 >= 0 &&
                    node.getRow() + 1 <= MapInfo.map_rows &&
                    node.getCol() + 1 <= MapInfo.map_cols) {
                if (node.getRow() - 1 <= user_row ||
                        user_row <= node.getRow() + 1 ||
                        node.getCol() - 1 <= user_col ||
                        user_col <= node.getCol() + 1) {
                    check_Position = true;
                }
            }
        }
        if (check_Position) {
            return true;
        } else {
            return false;
        }
    }

    //최소의 F값 찾기
    private int compare_Minimum(int[] means_transportation) {
        int find_Minimum = means_transportation[0];
        for (int i = 1; i < means_transportation.length; i++) {
            if (find_Minimum >= means_transportation[i]) {
                find_Minimum = means_transportation[i];
            }
        }

        int find_component;

        for (find_component = 0; find_component < means_transportation.length; find_component++) {
            if (find_Minimum == means_transportation[find_component])
                break;
        }

        return find_component;
    }

    //사용자 현재 위치의 노드 얻긴
    public Node get_currentNode() {
        Node current_Node = null;
        IndoorAtlas.latitude -= 37.54715706;
        IndoorAtlas.longitude -= 127.07383858;

        double b = 93806;
        double a = 158983;
        double c = 184595;

        IndoorAtlas.latitude = ((a / c) * IndoorAtlas.latitude) + ((b / c) * IndoorAtlas.longitude) ;
        IndoorAtlas.longitude  = ((-b / c) * IndoorAtlas.latitude) + ((a / c) * IndoorAtlas.longitude) ;

        int count_row = Integer.parseInt(String.valueOf(IndoorAtlas.latitude / 0.00001999));
        int count_col = Integer.parseInt(String.valueOf(IndoorAtlas.longitude / 0.00002148));

        current_Node = new Node(count_row, count_col);
        current_Node.setFloor(IndoorAtlas.floor);

        if (count_row < 0 || count_col < 0) {
            return null;
        } else {
            return current_Node;
        }
    }

    //목적지에 대한 노드 얻기
    public Node get_destinationNode(String destination) {
        Node final_Node = null;

        switch (destination) {
            case "남자화장실":
                final_Node.setFloor(-2);
                break;
            case "여자화장실":
                final_Node.setFloor(-2);
                break;
            case "건대입구방향 스크린도어":
                final_Node.setFloor(-3);
                break;
            case "군자방향 스크린도어":
                final_Node.setFloor(-3);
                break;
            case "1번 출구":
                final_Node.setFloor(-1);
                break;
            case "2번 출구":
                final_Node.setFloor(-1);
                break;
            case "3번 출구":
                final_Node.setFloor(-1);
                break;
            case "4번 출구":
                final_Node.setFloor(-1);
                break;
            case "5번 출구":
                final_Node.setFloor(-1);
                break;
            case "6번 출구":
                final_Node.setFloor(-1);
                break;
        }
        return final_Node;
    }

    public String get_Device_bearing(double bearing) {
        return "";
    }

    public void get_Next_Path() {
    }
}
