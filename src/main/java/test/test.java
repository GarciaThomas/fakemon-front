package test;

import java.util.ArrayList;

import model.Context;

public class test {
public static void main(String[] args) {
	ArrayList<Integer> ids = new ArrayList<Integer>();
	ids.add(1);
	System.out.println(Context.getInstance().getDAOAtk().selectPoolId(ids));
}
}
