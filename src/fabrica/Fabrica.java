/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fabrica;
import gurobi.*;
/**
 *
 * @author Jeferson
 */
public class Fabrica {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            GRBEnv env = new GRBEnv("Fabrica.log");
            GRBModel model = new GRBModel(env);
            
            //variaveis do probleba, seus indices e o nome correspondente
            GRBVar x = model.addVar(0, GRB.INFINITY, 100, GRB.INTEGER, "P1");
            GRBVar y = model.addVar(0, GRB.INFINITY, 150, GRB.INTEGER, "P2");
            
            //FO
            model.set(GRB.IntAttr.ModelSense, GRB.MAXIMIZE);
            
            //restriçoes
            // 1) 2x + 3y <= 120
            GRBLinExpr expr1 = new GRBLinExpr();
            expr1.addTerm(2, x); expr1.addTerm(3, y);
            model.addConstr(expr1, GRB.LESS_EQUAL, 120, "Tempo");
            
            //2) x <=40
            GRBLinExpr expr2 = new GRBLinExpr();
            expr2.addTerm(1, x);
            model.addConstr(expr2, GRB.LESS_EQUAL, 40, "Produção de P1");
            
            //3) y<=30
            GRBLinExpr expr3 = new GRBLinExpr();
            expr3.addTerm(30, y);
            model.addConstr(expr3, GRB.LESS_EQUAL, 30, "Produção de P2");
            
            //otimização do modelo
            model.optimize();
            
            //impressão da solução
            System.out.println(model.getVars());
            System.out.println(x.get(GRB.StringAttr.VarName) + " " + x.get(GRB.DoubleAttr.X));
            System.out.println(y.get(GRB.StringAttr.VarName) + " " + y.get(GRB.DoubleAttr.X));
            System.out.println("Obj: " + model.get(GRB.DoubleAttr.ObjVal));
            
            
        } catch (Exception e) {
        }
    }
    
}
