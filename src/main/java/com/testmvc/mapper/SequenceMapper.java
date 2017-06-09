package com.testmvc.mapper;


import com.testmvc.domain.Sequence;

/**
 * The Interface SequenceMapper.
 *
 */
public interface SequenceMapper {

  Sequence getSequence(Sequence sequence);

  void updateSequence(Sequence sequence);
}
