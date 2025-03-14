import java.util.*;
import java.util.stream.Collectors;

class CollectionUtils 
{
    public static Map<String, Integer> sortMapByValue(Map<String, Integer> map) 
    {
        return map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }

    public static Map<String, Integer> sumMaps(Map<String, Integer> map1, Map<String, Integer> map2) 
    {
        Map<String, Integer> result = new HashMap<>(map1);
        for (Map.Entry<String, Integer> entry : map2.entrySet()) 
            result.merge(entry.getKey(), entry.getValue(), Integer::sum);
        return result;
    }

    public static Set<Integer> findDuplicatesInList(List<Integer> list) 
    {
        Set<Integer> duplicates = new HashSet<>();
        Set<Integer> seen = new HashSet<>();
        for (Integer value : list) 
        {
            if (!seen.add(value)) 
                duplicates.add(value);            
        }
        return duplicates;
    }

    public static Set<Integer> intersectionOfSets(Set<Integer> set1, Set<Integer> set2) 
    {
        Set<Integer> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);
        return intersection;
    }

    public static void main(String[] args) 
    {
        // Сортировка map по значению
        Map<String, Integer> mapToSort = new HashMap<>();
        mapToSort.put("A", 3);
        mapToSort.put("B", 1);
        mapToSort.put("C", 2);
        System.out.println("Отсортированная Map: " + sortMapByValue(mapToSort));

        // Сумма двух map
        Map<String, Integer> map1 = new HashMap<>();
        map1.put("A", 1);
        map1.put("B", 2);
        
        Map<String, Integer> map2 = new HashMap<>();
        map2.put("A", 3);
        map2.put("C", 4);
        System.out.println("Суммированная Map: " + sumMaps(map1, map2));

        // Поиск дубликатов в списке
        List<Integer> listWithDuplicates = Arrays.asList(1, 2, 3, 1, 2, 4);
        System.out.println("Дубликаты в списке: " + findDuplicatesInList(listWithDuplicates));

        // Пересечение двух сетов
        Set<Integer> set1 = new HashSet<>(Arrays.asList(1, 2, 3));
        Set<Integer> set2 = new HashSet<>(Arrays.asList(3, 4, 5));
        System.out.println("Пересечение множеств: " + intersectionOfSets(set1, set2));
    }
}