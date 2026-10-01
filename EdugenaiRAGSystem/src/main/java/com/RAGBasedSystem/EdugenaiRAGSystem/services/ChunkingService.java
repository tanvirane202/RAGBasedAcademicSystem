package com.RAGBasedSystem.EdugenaiRAGSystem.services;



import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ChunkingService {
	

	
	public List<String> createChunks(String text) {

		
		List<String> chunks = new ArrayList<>();
		int chunkSize=500;
		int overlap=100;
		
		for(int start = 0; start < text.length(); )
	{
		int end =Math.min(start+chunkSize,text.length());
		String chunk=text.substring(start,end);
		chunks.add(chunk);
		start=start+chunkSize-overlap;
	}
		return chunks;
}
}
