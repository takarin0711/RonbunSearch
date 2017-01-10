package com.example.inohizatakayuki.ronbunsearch;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import edu.cmu.lti.jawjaw.JAWJAW;
import edu.cmu.lti.jawjaw.pobj.POS;

/**
 * Created by Inohiza Takayuki on 2017/01/08.
 */

public class SimpleDemo {
    private static void run( String word, POS pos ) {
        // ファサードから日本語 WordNet にアクセス
        Set<String> hypernyms = JAWJAW.findHypernyms(word, pos);
        Set<String> hyponyms = JAWJAW.findHyponyms(word, pos);
        Set<String> consequents = JAWJAW.findEntailments(word, pos);
        Set<String> translations = JAWJAW.findTranslations(word, pos);
        Set<String> definitions = JAWJAW.findDefinitions(word, pos);
        // 結果表示（多義語はごっちゃになっています）
        System.out.println( "hypernyms of "+word+" : \t"+ hypernyms );
        Iterator it = hypernyms.iterator();
        ArrayList<String> array = new ArrayList<String>();
        while (it.hasNext()) {
            array.add((String) it.next());
        }
        int i;
        for(i=0;i<array.size(); i++){
            System.out.println( array.get(i) );
        }
        // System.out.println( "hyponyms of "+word+" : \t"+ hyponyms );
        // System.out.println( word+" entails : \t\t"+ consequents );
        // System.out.println( "translations of "+word+" : \t"+ translations );
        // System.out.println( "definitions of "+word+" : \t"+ definitions );
    }
}
